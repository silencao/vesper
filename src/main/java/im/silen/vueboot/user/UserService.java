package im.silen.vueboot.user;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private final UserMapper userMapper;

    private UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userMapper.search(username);
    }

    public void createUser(User user) {
    }

    public void updateUser(UserDetails user) {

    }

    public void deleteUser(String username) {

    }

    public void changePassword(String oldPassword, String newPassword) {

    }

    public boolean userExists(String username) {
        return false;
    }
}
