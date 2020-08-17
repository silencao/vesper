package im.silen.vueboot;

import im.silen.vueboot.util.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final DataSource dataSource;

    public WebSecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    @Override
    public JdbcUserDetailsManager userDetailsServiceBean() {
        return new JdbcUserDetailsManager(dataSource);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        AuthenticationEntryPoint     authenticationEntryPoint     = (request, response, authException ) -> handlerWriteString(response, HttpServletResponse.SC_UNAUTHORIZED, "需要登录！");
        AuthenticationSuccessHandler authenticationSuccessHandler = (request, response, authentication) -> handlerWriteString(response, HttpServletResponse.SC_OK          , "登录成功！");
        AuthenticationFailureHandler authenticationFailureHandler = (request, response, exception     ) -> handlerWriteString(response, HttpServletResponse.SC_BAD_REQUEST , "登录失败！");

        http
                .csrf(configurer -> configurer
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .authorizeRequests(configure -> configure
                        .anyRequest().authenticated())
                .exceptionHandling(configure -> configure
                        .authenticationEntryPoint(authenticationEntryPoint))
                .formLogin(configurer -> configurer
                        .loginProcessingUrl("/doLogin")
                        .successHandler(authenticationSuccessHandler)
                        .failureHandler(authenticationFailureHandler))
                .rememberMe(configurer -> configurer
                        .tokenValiditySeconds(60));
    }

    private void handlerWriteString(HttpServletResponse response, int errCode, String errMsg) throws IOException {
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
        auth.userDetailsService(userDetailsServiceBean()).passwordEncoder(passwordEncoder());
    }
}
