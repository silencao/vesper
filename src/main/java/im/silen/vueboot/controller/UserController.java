package im.silen.vueboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserDetailsManager userManager;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private UserController(UserDetailsManager userManager) {
        this.userManager = userManager;
    }

    @GetMapping("/user/add")
    public String addUser(@RequestParam String name) {
        userManager.createUser(User.builder().username(name).password(passwordEncoder.encode("123456")).roles("USER").build());
        return userManager.getClass().getName();
    }
}
