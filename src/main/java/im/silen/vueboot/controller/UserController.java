package im.silen.vueboot.controller;

import im.silen.vueboot.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(){
        return "未登录";
    }
    @GetMapping("/user/add")
    public String add(@RequestParam String name) {

        return userService.addUser(name, "123456");
    }
}
