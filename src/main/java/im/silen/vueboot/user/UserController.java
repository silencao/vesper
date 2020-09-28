package im.silen.vueboot.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/user/add")
    public String add(
            @RequestParam User user
    ) {
        userService.createUser(user);

        return "ok";
    }
}
