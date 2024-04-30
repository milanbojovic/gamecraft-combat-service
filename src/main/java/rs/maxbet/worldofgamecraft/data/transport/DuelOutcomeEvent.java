package rs.maxbet.worldofgamecraft.data.transport;

import lombok.Data;
import rs.maxbet.worldofgamecraft.data.Duel;

@Data
public class DuelOutcomeEvent {
    private int id;
    private int challengerId;
    private int challengeeId;
    private String outcome;

    public DuelOutcomeEvent(Duel duel) {
        this.id = duel.getId();
        this.challengerId = duel.getChallenger().getId();
        this.challengeeId = duel.getChallengee().getId();
        this.outcome = duel.getOutcome().name();
    }
}
