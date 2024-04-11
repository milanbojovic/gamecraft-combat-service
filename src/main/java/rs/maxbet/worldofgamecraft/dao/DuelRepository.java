package rs.maxbet.worldofgamecraft.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.maxbet.worldofgamecraft.data.Duel;
import rs.maxbet.worldofgamecraft.enumerations.Enumerations;

import java.util.List;
import java.util.Optional;

public interface DuelRepository extends JpaRepository<Duel, Long> {
    Optional<Duel> findByChallengerIdAndStatus(int challengerId, Enumerations.Status status);
    Optional<Duel> findByChallengeeIdAndStatus(int challengee, Enumerations.Status status);

    Optional<Duel> findByChallengeeId(int chalengeeId);

    List<Duel> findAllByStatus(Enumerations.Status status);

}
