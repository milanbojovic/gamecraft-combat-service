package rs.maxbet.worldofgamecraft.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.maxbet.worldofgamecraft.data.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {}
