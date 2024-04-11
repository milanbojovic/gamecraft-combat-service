package rs.maxbet.worldofgamecraft.data;

import jakarta.persistence.*;
import lombok.Data;
import rs.maxbet.worldofgamecraft.enumerations.Enumerations;

import java.sql.Timestamp;

@Entity
@Table(schema = "combat_schema", name = "duel")
@Data
public class Duel {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "challenger_id", referencedColumnName = "id")
    private Character challenger;
    @OneToOne
    @JoinColumn(name = "challengee_id", referencedColumnName = "id")
    private Character challengee;
    private Timestamp started;
    private Timestamp completed;
    @Enumerated(EnumType.STRING)
    private Enumerations.Status status;
    @Enumerated(EnumType.STRING)
    private Enumerations.Outcome outcome;
    @Enumerated(EnumType.STRING)
    private Enumerations.State challengerState;
    @Enumerated(EnumType.STRING)
    private Enumerations.State challengeeState;
}
