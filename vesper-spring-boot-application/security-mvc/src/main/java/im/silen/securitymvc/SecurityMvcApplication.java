package im.silen.securitymvc;

import im.silen.vesper.lib.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

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
        @GetMapping("/") Object main() {
            return IntStream.rangeClosed(1,10).summaryStatistics();
        }
    }

    @Bean
    public ErrorController errorController(ErrorAttributes errorAttributes, ServerProperties serverProperties, List<ErrorViewResolver> errorViewResolvers) {

        return new BasicErrorController(errorAttributes, serverProperties.getError(), errorViewResolvers) {
            @Override
            public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
                return ResponseEntity.status(getStatus(request)).body(Collections.singletonMap("now", LocalDateTime.now()));
            }

            @Override
            @RequestMapping("/*.html")
            public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
                return super.errorHtml(request, response);
            }

            @GetMapping("/t1/{id}")
            public Object returnID(@PathVariable int id) {
                return ResponseEntity.ok(String.format("%d", id));
            }
        };
    }

    @EnableWebSecurity
    static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests().antMatchers("/").anonymous()
                    .antMatchers("/hasError/**").permitAll()
                    .antMatchers("**").authenticated();
            http.exceptionHandling().authenticationEntryPoint((request, response, authException) -> {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "需要登录");
//                response.getWriter().append(JSONObject.stringify(Collections.singletonMap("now", LocalDateTime.now())));
//                容器会自动关闭
//                response.getWriter().flush();
//                response.getWriter().close();
            });
            http.formLogin()
//                     .loginPage         (/*GET , 默认值*/"/login")
//                     .loginProcessingUrl(/*POST, 默认值*/"/login")
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
