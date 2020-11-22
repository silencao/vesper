package im.silen.vueboot.user;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/search/{username}")
    public User search(@PathVariable String username) {
        return userService.loadUserByUsername(username);
    }

    @GetMapping("/user/searchAll/{username}")
    public List<User> searchAll(@PathVariable String username) {
        return userService.searchAll(username);
    }

    @PostMapping("/user/add")
    public User add(@RequestBody User user) {
        return userService.createUser(user.getUsername(), user.getPassword());
    }
}
