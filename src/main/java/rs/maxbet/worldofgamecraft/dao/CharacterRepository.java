package rs.maxbet.worldofgamecraft.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.maxbet.worldofgamecraft.data.Character;

public interface CharacterRepository extends JpaRepository<Character, Long> {}
