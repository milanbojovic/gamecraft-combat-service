package rs.maxbet.worldofgamecraft.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import rs.maxbet.worldofgamecraft.data.Character;
import rs.maxbet.worldofgamecraft.data.transport.CharacterCreationEvent;
import rs.maxbet.worldofgamecraft.service.CharacterService;

@Component
public class CharacterCreationListener {

    private static final Logger logger = LoggerFactory.getLogger(CharacterCreationListener.class);

    @Autowired
    private final CharacterService characterService;

    public CharacterCreationListener(CharacterService characterService) {
        this.characterService = characterService;
    }

    @RabbitListener(queues = {"${rabbitmq.queue.character.creation}"})
    public void handleMessage(CharacterCreationEvent characterCreationEvent) {
        logger.info("Received user character creation event: " + characterCreationEvent);
        this.characterService.saveCharacter(new Character(characterCreationEvent));
    }
}
