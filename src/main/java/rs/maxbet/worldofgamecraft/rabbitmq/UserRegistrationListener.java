package rs.maxbet.worldofgamecraft.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import rs.maxbet.worldofgamecraft.data.Users;
import rs.maxbet.worldofgamecraft.data.transport.UserRegistrationEvent;
import rs.maxbet.worldofgamecraft.service.UserService;

@Component
public class UserRegistrationListener {

    private static final Logger logger = LoggerFactory.getLogger(UserRegistrationListener.class);

    @Autowired
    private final UserService userService;
    @Value("${rabbitmq.queue.combat.user.registration}")
    String queueCharacterUserRegistration;

    public UserRegistrationListener(UserService userService) {
        this.userService = userService;
    }

    @RabbitListener(queues = {"${rabbitmq.queue.combat.user.registration}"})
    public void handleMessage(UserRegistrationEvent registrationEvent) {
        logger.info("Received user registration event: " + registrationEvent);
        this.userService.saveUser(new Users(registrationEvent));
    }
}
