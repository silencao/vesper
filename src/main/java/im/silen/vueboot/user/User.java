package im.silen.vueboot.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String username;
    private String password;
    private boolean enabled;
    private LocalDateTime registrationDatetime;

    protected User() {
    }
    public User(String username, String password, boolean enabled, LocalDateTime registrationDatetime) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.registrationDatetime = registrationDatetime;
    }

    @Override
    public List<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }
    public Long getUserId() {
        return userId;
    }
    public LocalDateTime getRegistrationDatetime() {
        return registrationDatetime;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
