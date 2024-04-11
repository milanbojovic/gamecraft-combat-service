package rs.maxbet.worldofgamecraft.controler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import rs.maxbet.worldofgamecraft.data.Duel;
import rs.maxbet.worldofgamecraft.data.Users;
import rs.maxbet.worldofgamecraft.data.Character;
import rs.maxbet.worldofgamecraft.enumerations.Enumerations;
import rs.maxbet.worldofgamecraft.exception.CharacterNotFoundException;
import rs.maxbet.worldofgamecraft.exception.UserNotCharacterOwnerException;
import rs.maxbet.worldofgamecraft.service.CharacterService;
import rs.maxbet.worldofgamecraft.service.DuelService;

import java.util.*;

@RestController
@RequestMapping("/api")
public class DuelController {

    @Autowired
    private final DuelService duelService;
    @Autowired
    private final CharacterService characterService;

    public DuelController(DuelService duelService, CharacterService characterService) {
        this.duelService = duelService;
        //this.rabbitTemplate = rabbitTemplate;
        this.characterService = characterService;
    }

    @PreAuthorize("hasRole('ROLE_GameMaster') || hasRole('ROLE_User')")
    @PostMapping(value = "/challenge")
    public ResponseEntity<?> createChallenge(@RequestParam int challengerId, @RequestParam int challengeeId) {
        try {
            validateCharacterExists(challengerId);
            validateCharacterExists(challengeeId);
            validateCharacterOwnership(challengerId);
            duelService.validateCharactersNotInDuel(challengerId, challengeeId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    Collections.singletonMap("error", e.getMessage()));
        }
        return  ResponseEntity.ok().body(Collections.singletonMap("duelId", duelService.startDuel(challengerId, challengeeId)));
    }

    @PreAuthorize("hasRole('ROLE_GameMaster') || hasRole('ROLE_User')")
    @GetMapping(value = "/challenge")
    public List<Duel> getChallenges() {
        return  duelService.getDuels();
    }

    @PreAuthorize("hasRole('ROLE_GameMaster') || hasRole('ROLE_User')")
    @PostMapping(value = "/{characterId}/attack")
    public ResponseEntity<?> setCharacterAttackMode(@PathVariable int characterId) {
        try {
            validateCharacterExists(characterId);
            validateCharacterOwnership(characterId);
            duelService.setCharacterDuelMode(characterId, Enumerations.State.ATTACK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    Collections.singletonMap("error", e.getMessage()));
        }
        return ResponseEntity.ok().body(Collections.singletonMap("message", "   Character with id " + characterId + " is now in attack mode"));
    }

    @PreAuthorize("hasRole('ROLE_GameMaster') || hasRole('ROLE_User')")
    @PostMapping(value = "/{characterId}/cast")
    public ResponseEntity<?> setCharacterCastMode(@PathVariable int characterId) {
        try {
            validateCharacterExists(characterId);
            validateCharacterOwnership(characterId);
            duelService.setCharacterDuelMode(characterId, Enumerations.State.CAST);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    Collections.singletonMap("error", e.getMessage())
            );
        }
        return ResponseEntity.ok().body(Collections.singletonMap("message", "   Character with id " + characterId + " is now in cast mode"));
    }

    @PreAuthorize("hasRole('ROLE_GameMaster') || hasRole('ROLE_User')")
    @PostMapping(value = "/{characterId}/heal")
    public ResponseEntity<?> setCharacterHealMode(@PathVariable int characterId) {
        try {
            validateCharacterExists(characterId);
            validateCharacterOwnership(characterId);
            duelService.setCharacterDuelMode(characterId, Enumerations.State.HEAL);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    Collections.singletonMap("error", e.getMessage())
            );
        }
        return ResponseEntity.ok().body(Collections.singletonMap("message", "   Character with id " + characterId + " is now in heal mode"));
    }


    @PostMapping(value = "/dueliteration")
    public void duelIteration() {
        duelService.performCombat();
    }

    private void validateCharacterOwnership(int characterId) {
        Character character = characterService.getCharacterById((long) characterId);
        Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user.getId() != character.getCreatedBy()) {
            throw new UserNotCharacterOwnerException("Error you are not the owner of character with id " + characterId);
        }
    }

    private void validateCharacterExists(int characterId) {
        characterService.getCharacter(characterId).orElseThrow(() -> new CharacterNotFoundException("Character with id " + characterId + " not found"));
    }
}
