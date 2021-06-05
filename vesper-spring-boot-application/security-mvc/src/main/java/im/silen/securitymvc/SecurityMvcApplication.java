package im.silen.securitymvc;

import im.silen.vesper.lib.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.csrf.CsrfException;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootApplication
public class SecurityMvcApplication {
    public static void main(String[] args) { SpringApplication.run(SecurityMvcApplication.class, args); }

    @RestController
    static class Controller {
        @GetMapping("/hello") String hello(@AuthenticationPrincipal UserDetails user) {
            return "hello " + user.getUsername() + "!";
        }
        @PostMapping("/entity") String entity() {
            return JSONObject.stringify(ResponseEntity.status(401)
                    .header("x-custom", "test")
                    .body(LocalDateTime.now()));
        }
        @GetMapping("/") Object main() {
            return IntStream.rangeClosed(1,10).summaryStatistics();
        }
    }

    @EnableWebSecurity
    static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
        private final Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);
        private static class ResponseError {
            private final int status;
            private final String message;
            private final String exception;
            private final LocalDateTime timestamp = LocalDateTime.now();
            private JSONObject data;

            public ResponseError(int status, String message, String exception) {
                this.status = status;
                this.message = message;
                this.exception = exception;
            }
            public int getStatus() { return status;}
            public String getException() { return exception;}
            public LocalDateTime getTimestamp() { return timestamp;}

            public void setData(JSONObject data) {
                this.data = data;
            }

            public JSONObject getData() { return data; }
            public String getMessage() { return message; }
        }

        public SecurityConfiguration() { super(false); }

        @Bean
        public AuthenticationEventPublisher authenticationEventPublisher
                (ApplicationEventPublisher applicationEventPublisher) {
            return new DefaultAuthenticationEventPublisher(applicationEventPublisher);
        }

        private final CsrfTokenRepository csrfTokenRepository = new HttpSessionCsrfTokenRepository();

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/").anonymous()

                    .antMatchers("/hasError/**").permitAll()
                    .antMatchers("**").authenticated();

            http.csrf().csrfTokenRepository(csrfTokenRepository);
            http.exceptionHandling()
                    .accessDeniedHandler((request, response, accessDeniedException) -> {
                        JSONObject data;

                        if (accessDeniedException instanceof CsrfException)
                            data = new JSONObject().fluentPut("csrfToken", csrfTokenRepository.loadToken(request).getToken());
                        else
                            data = null;

                        sendError(HttpServletResponse.SC_BAD_REQUEST, data,
                                request, response, accessDeniedException);
                    })
                    .authenticationEntryPoint((request, response, authException) -> {
                        /* 配置后http.formLogin().loginPage()失效，已知以下几种可能的异常
                         * 1 org.springframework.security.authentication.InsufficientAuthenticationException: Full authentication is required to access this resource */
                        sendError(HttpServletResponse.SC_UNAUTHORIZED,
                                request, response, authException);
                    })
            ;

//            http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
            http.formLogin()
                    .failureHandler((request, response, exception) ->
                            sendError(HttpServletResponse.SC_BAD_REQUEST,
                                    request, response, exception))
                    .successHandler((request, response, authentication) ->
                            response.getWriter().println(authentication))
                    .loginProcessingUrl(/*POST, 默认值*/"/login");
//            http.headers().cacheControl();
        }

        private void sendError(int status, HttpServletRequest request, HttpServletResponse response, Exception exception) throws IOException {
            sendError(status, null, request, response, exception);
        }
        private void sendError(int status, JSONObject data, HttpServletRequest request, HttpServletResponse response, Exception exception) throws IOException {
            logger.info("{} {} SessionId: {} URI: {}", "", exception.getMessage(), Optional.ofNullable(request.getRequestedSessionId())
                    .map(id -> id.substring(0, 8) + "...")
                    .orElse("暂未获取,"), request.getRequestURI());

            ResponseError error = new ResponseError(status, null, exception.getMessage());

            if (data != null)
                error.setData(data);

            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setStatus(status);
            response.getWriter().println(JSONObject.stringify(error));
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
