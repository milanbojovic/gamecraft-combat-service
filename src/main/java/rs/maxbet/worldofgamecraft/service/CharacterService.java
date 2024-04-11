package rs.maxbet.worldofgamecraft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.maxbet.worldofgamecraft.dao.CharacterRepository;
import rs.maxbet.worldofgamecraft.dao.UsersRepository;
import rs.maxbet.worldofgamecraft.data.Character;

import java.util.Optional;

@Service
public class CharacterService {

    @Autowired
    private final CharacterRepository characterRepository;

    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }
    public Character getCharacterById(Long id) {
        return characterRepository.findById(id).orElse(null);
    }

    public Optional<Character> getCharacter(int characterId) {
        return characterRepository.findById((long) characterId);
    }

    public void saveCharacter(Character character) {
        characterRepository.save(character);
    }
}