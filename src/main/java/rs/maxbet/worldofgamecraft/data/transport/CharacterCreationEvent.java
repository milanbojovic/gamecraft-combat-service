package rs.maxbet.worldofgamecraft.data.transport;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.maxbet.worldofgamecraft.data.Character;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CharacterCreationEvent {
    private int id;
    private String name;
    private int health;
    private int mana;
    private int baseStrength;
    private int baseAgility;
    private int baseIntelligence;
    private int baseFaith;
    private int createdBy;
}
