package im.silen.vueboot.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final JdbcUserDetailsManager userManager;
    private final PasswordEncoder passwordEncoder;

    private UserService(JdbcUserDetailsManager userManager, PasswordEncoder passwordEncoder) {
        this.userManager = userManager;
        this.passwordEncoder = passwordEncoder;
    }

    public String addUser(String username, String password) {
        userManager.createUser(User.withUsername(username).password(passwordEncoder.encode(password)).roles("USER").build());
        return "ok";
    }
}
