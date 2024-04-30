package rs.maxbet.worldofgamecraft.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import rs.maxbet.worldofgamecraft.data.transport.CharacterCreationEvent;

@Entity
@Table
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Character {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String name;
    private int health;
    private int mana;
    private int baseStrength;
    private int baseAgility;
    private int baseIntelligence;
    private int baseFaith;
    private int createdBy;

    public Character(CharacterCreationEvent characterCreationEvent) {
        this.id = characterCreationEvent.getId();
        this.name = characterCreationEvent.getName();
        this.health = characterCreationEvent.getHealth();
        this.mana = characterCreationEvent.getMana();
        this.baseStrength = characterCreationEvent.getBaseStrength();
        this.baseAgility = characterCreationEvent.getBaseAgility();
        this.baseIntelligence = characterCreationEvent.getBaseIntelligence();
        this.baseFaith = characterCreationEvent.getBaseFaith();
        this.createdBy = characterCreationEvent.getCreatedBy();
    }
}
