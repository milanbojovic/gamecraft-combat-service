//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package rs.maxbet.worldofgamecraft.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.maxbet.worldofgamecraft.data.Users;
import rs.maxbet.worldofgamecraft.service.UserService;

import java.util.List;

@RestController
@RequestMapping({"/api"})
public class UserController {
    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/users"})
    public List<Users> getAllUsers() {
        return this.userService.getAllUsers();
    }
}
