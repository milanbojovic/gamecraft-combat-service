package rs.maxbet.worldofgamecraft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.maxbet.worldofgamecraft.dao.UsersRepository;
import rs.maxbet.worldofgamecraft.data.Users;

@Service
public class UserService {

    @Autowired
    private final UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
    public Users getUserById(Long id) {
        return usersRepository.findById(id).orElse(null);
    }
}