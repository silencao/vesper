package im.silen.vueboot.controller;

import im.silen.vueboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;
    private final StringRedisTemplate redisTemplate;

    public UserController(UserService userService, StringRedisTemplate redisTemplate) {
        this.userService = userService;
        this.redisTemplate = redisTemplate;
    }

    @RequestMapping("/success")
    public String success() {
        return "登录成功";
    }

    @GetMapping("/login")
    public String login() {
        return "未登录";
    }

    @GetMapping("/user/add")
    public String add(@RequestParam String name) {
        redisTemplate.opsForList().leftPush("username", name);

        return userService.addUser(name, "123456");
    }
}
