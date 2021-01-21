package im.silen.securitymvc;

import im.silen.vesper.lib.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@SpringBootApplication
public class SecurityMvcApplication {
    public static void main(String[] args) { SpringApplication.run(SecurityMvcApplication.class, args); }

    @RestController
    static class Controller {
        @GetMapping("/hello") String hello(@AuthenticationPrincipal UserDetails user) {
            return "hello " + user.getUsername() + "!";
        }
        @GetMapping("/entity") String entity() {
            return JSONObject.stringify(ResponseEntity.status(401)
                    .header("x-custom", "test")
                    .body(LocalDateTime.now()));
        }
    }

    @EnableWebSecurity
    static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests().anyRequest().authenticated();
            http.formLogin()
                     .loginPage         (/*GET , 默认值*/"/login")
                     //.loginProcessingUrl(/*POST, 默认值*/"/login")
            .failureHandler((request, response, exception) -> response.sendError(HttpStatus.UNAUTHORIZED.value(), exception.getMessage()));
            http.headers().cacheControl();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication()
                    .withUser("admin").password("{noop}123").roles("ADMIN").and()
                    .withUser("super").password("{noop}qwe").roles("SUPER").and()
                    .withUser("user" ).password("{noop}asd").roles("USER" );
        }
    }
}
