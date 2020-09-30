package im.silen.vueboot.user;

import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/user/add")
    public User add(@RequestBody User user) {
        userService.createUser(user.getUsername(), user.getPassword());

        return user;
    }
}
