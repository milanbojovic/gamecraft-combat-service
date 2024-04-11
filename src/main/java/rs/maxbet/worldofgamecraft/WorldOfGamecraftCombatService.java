package rs.maxbet.worldofgamecraft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WorldOfGamecraftCombatService {
    public static void main(String[] args) {
        SpringApplication.run(WorldOfGamecraftCombatService.class, args);
    }
}