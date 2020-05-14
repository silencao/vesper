package im.silen.vueboot.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @CrossOrigin(value = {"http://localhost:3000"})
    @GetMapping("/needLogin")
    public String login() {
        return "login";
    }
}
