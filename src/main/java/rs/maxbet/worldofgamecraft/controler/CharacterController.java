//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package rs.maxbet.worldofgamecraft.controler;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.maxbet.worldofgamecraft.data.Character;
import rs.maxbet.worldofgamecraft.service.CharacterService;

import java.util.List;

@RestController
@RequestMapping({"/api"})
public class CharacterController {
    @Autowired
    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping({"/characters"})
    public List<Character> getAllCharactersFromDb() {
        return this.characterService.getAllCharacters();
    }
}
