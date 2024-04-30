package rs.maxbet.worldofgamecraft.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import rs.maxbet.worldofgamecraft.data.transport.DuelOutcomeEvent;

@Service
public class MessageService {
    
    private final AmqpTemplate amqpTemplate;

    @Value("${rabbitmq.queue.combat.outcome}")
    String queueCombatOutcome;

    public MessageService(RabbitTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void publishDuelOutcomeEvent(DuelOutcomeEvent duelOutcomeEvent) {
        amqpTemplate.convertAndSend("", queueCombatOutcome,  duelOutcomeEvent);
    }
}