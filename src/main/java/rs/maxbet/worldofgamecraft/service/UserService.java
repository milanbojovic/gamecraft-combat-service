package rs.maxbet.worldofgamecraft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.maxbet.worldofgamecraft.dao.UsersRepository;
import rs.maxbet.worldofgamecraft.data.Users;

import java.util.List;

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

    public List<Users> getAllUsers() {
        return this.usersRepository.findAll();
    }

    public Users saveUser(Users user) {
        return (Users)this.usersRepository.save(user);
    }
}