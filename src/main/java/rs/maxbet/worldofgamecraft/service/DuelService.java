package rs.maxbet.worldofgamecraft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import rs.maxbet.worldofgamecraft.dao.DuelRepository;
import rs.maxbet.worldofgamecraft.data.Character;
import rs.maxbet.worldofgamecraft.data.Duel;
import rs.maxbet.worldofgamecraft.enumerations.Enumerations;
import rs.maxbet.worldofgamecraft.exception.CharacterAlreadyInDuelException;
import rs.maxbet.worldofgamecraft.exception.CharacterNotFoundException;
import rs.maxbet.worldofgamecraft.exception.CharacterNotInDuelException;

import java.sql.Timestamp;
import java.util.*;

@Service
public class DuelService {

    @Autowired
    private final DuelRepository duelRepository;
    @Autowired
    private final CharacterService characterService;

    private static int actionCounter;

    public DuelService(DuelRepository duelRepository, CharacterService characterService) {
        this.duelRepository = duelRepository;
        this.characterService = characterService;
        actionCounter = 0;
    }

    public List<Duel> getDuels() {
        return duelRepository.findAll();
    }

    public int startDuel(int challengerId, int challengeeId) {
        Optional<Character> challengerCharacter = characterService.getCharacter(challengerId);
        Optional<Character> challengeeCharacter = characterService.getCharacter(challengeeId);

        Duel duel = new Duel();
        duel.setChallenger(challengerCharacter.orElseThrow(() -> new CharacterNotFoundException("Character with id " + challengerId + " not found")));
        duel.setChallengee(challengeeCharacter.orElseThrow(() -> new CharacterNotFoundException("Character with id " + challengeeId + " not found")));

        Timestamp startedTime = new Timestamp(System.currentTimeMillis());
        duel.setStarted(startedTime);

        duel.setCompleted(calculateLatestCompletionTime(startedTime));

        duel.setStatus(Enumerations.Status.IN_PROGRESS);
        duel.setChallengerState(Enumerations.State.ATTACK);
        duel.setChallengeeState(Enumerations.State.ATTACK);

        duelRepository.save(duel);
        return duel.getId();
    }

    public void validateCharactersNotInDuel(int challengerId, int challengeeId) {
        duelRepository.findAllByStatus(Enumerations.Status.IN_PROGRESS)
                .stream()
                .filter(duel -> duel.getChallenger().getId() == challengerId || duel.getChallengee().getId() == challengerId ||
                        duel.getChallenger().getId() == challengeeId || duel.getChallengee().getId() == challengeeId)
                .findFirst()
                .ifPresent(duel -> {
                    throw new CharacterAlreadyInDuelException("Character with id " + challengerId + " or " + challengeeId + " is already in a duel");
                });
    }


    private Optional<Duel> findDuelByCharacterIdAndStatus(int characterId, Enumerations.Status status) {
        Optional<Duel> optDuelByChallenger = duelRepository.findByChallengerIdAndStatus(characterId, status);
        Optional<Duel> optDuelByChallengee = duelRepository.findByChallengeeIdAndStatus(characterId, status);
        return optDuelByChallenger.isPresent() ? optDuelByChallenger : optDuelByChallengee;
    }

    private static Timestamp calculateLatestCompletionTime(Timestamp startedTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(startedTime.getTime());
        cal.add(Calendar.MINUTE, 5);
        return new Timestamp(cal.getTime().getTime());
    }

    @Scheduled(fixedRate = 1000)
    public void performCombat() {
        List<Duel> duels = duelRepository.findAllByStatus(Enumerations.Status.IN_PROGRESS);
        for (Duel duel : duels) {
            actionCounter++;
            if(actionCounter > 1000) actionCounter = 0;
            performAction(duel, duel.getChallenger(), duel.getChallengerState(), actionCounter);
            performAction(duel, duel.getChallengee(), duel.getChallengeeState(), actionCounter);

            // Check if duel has lasted more than 5 minutes
            if (new Timestamp(System.currentTimeMillis()).after(duel.getCompleted())) {
                duel.setStatus(Enumerations.Status.FINISHED);
                duel.setOutcome(Enumerations.Outcome.DRAW);
            }
            duelRepository.save(duel);
        }
    }

    private void performAction(Duel duel, Character character, Enumerations.State state, int counter) {
        Character otherCharacter = character.equals(duel.getChallenger()) ? duel.getChallengee() : duel.getChallenger();
        switch (state) {
            case ATTACK:
                otherCharacter.setHealth(otherCharacter.getHealth() - (character.getBaseStrength() + character.getBaseAgility()));
                break;
            case CAST:
            case HEAL:
                if (counter % 2 == 0) {
                    if (state == Enumerations.State.CAST) {
                        otherCharacter.setHealth(otherCharacter.getHealth() - (2 * character.getBaseIntelligence()));
                    } else {
                        character.setHealth(character.getHealth() + character.getBaseFaith());
                    }
                }
                break;
        }
        // Check if character's health has reached zero
        if (character.getHealth() <= 0) {
            duel.setStatus(Enumerations.Status.FINISHED);
            if (character.equals(duel.getChallenger())) {
                duel.setOutcome(Enumerations.Outcome.CHALLENGEE_WON);
            } else {
                duel.setOutcome(Enumerations.Outcome.CHALLENGER_WON);
            }
        }
        // Save the updated characters
        characterService.saveCharacter(character);
        characterService.saveCharacter(otherCharacter);
    }

    public void setCharacterDuelMode(int characterId, Enumerations.State state) {
        characterService.getCharacter(characterId).orElseThrow(
                () -> new CharacterNotFoundException("Character with id " + characterId + " not found"));
        Duel duel = findDuelByCharacterIdAndStatus(characterId,Enumerations.Status.IN_PROGRESS)
                .orElseThrow(() -> new CharacterNotInDuelException("Character with id " + characterId + " is not in a duel"));
        if (duel.getChallenger().getId() == characterId) {
            duel.setChallengerState(state);
        } else {
            duel.setChallengeeState(state);
        }
        duelRepository.save(duel);
    }
}