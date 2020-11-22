package im.silen.vueboot;

import im.silen.vueboot.csrf.CsrfRepository;
import im.silen.vueboot.user.UserService;
import im.silen.vueboot.util.JSONObject;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public WebSecurityConfig(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        AuthenticationEntryPoint     authenticationEntryPoint     = (request, response, authException ) -> writeAsSpecialStatusCode(response, HttpServletResponse.SC_UNAUTHORIZED, "需要登录！");
        AuthenticationSuccessHandler authenticationSuccessHandler = (request, response, authentication) -> writeAsSpecialStatusCode(response, HttpServletResponse.SC_OK          , "登录成功！");
        AuthenticationFailureHandler authenticationFailureHandler = (request, response, exception     ) -> writeAsSpecialStatusCode(response, HttpServletResponse.SC_BAD_REQUEST , "登录失败！");

        CookieCsrfTokenRepository cookieCsrfTokenRepository = CookieCsrfTokenRepository.withHttpOnlyFalse();
        cookieCsrfTokenRepository.setCookieName(CsrfRepository.DEFAULT_CSRF_COOKIE_NAME);
        cookieCsrfTokenRepository.setHeaderName(CsrfRepository.DEFAULT_CSRF_HEADER_NAME);

        http
                .sessionManagement(configure -> configure.maximumSessions(1))
                .csrf().csrfTokenRepository(cookieCsrfTokenRepository)
                .and().authorizeRequests(configure -> configure
                        .anyRequest().authenticated())
                .exceptionHandling(configure -> configure
                        .authenticationEntryPoint(authenticationEntryPoint))
                .formLogin(configurer -> configurer
                        .loginProcessingUrl("/doLogin")
                        .successHandler(authenticationSuccessHandler)
                        .failureHandler(authenticationFailureHandler))
                .rememberMe(configurer -> configurer
                        .tokenValiditySeconds(7 * 24 * 60 * 60));
    }

    private void writeAsSpecialStatusCode(HttpServletResponse response, int errCode, String errMsg) throws IOException {
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(errCode);
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("errCode", errCode);
        hashMap.put("errMsg" , errMsg);
        PrintWriter pw = response.getWriter();
        pw.write(JSONObject.stringify(hashMap));
        pw.flush();
        pw.close();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }
}
