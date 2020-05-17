package im.silen.vueboot;

import com.fasterxml.jackson.databind.ObjectMapper;
import im.silen.vueboot.util.JSONObject;
import org.apache.catalina.manager.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
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
    private final ObjectMapper mapper;

    public WebSecurityConfig(DataSource dataSource, ObjectMapper mapper) {
        this.dataSource = dataSource;
        this.mapper = mapper;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    protected JdbcUserDetailsManager userDetailsService() {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
                .authorizeRequests(configure -> configure
                        .antMatchers(HttpMethod.GET, "/index*").permitAll()
                        .anyRequest().authenticated())
                .exceptionHandling(configure -> configure
                        .authenticationEntryPoint((request, response, authException) -> handlerWriteString(response, HttpServletResponse.SC_UNAUTHORIZED, "需要登录！")))
                .formLogin(configurer -> configurer
                        .loginPage("/login")
                        .loginProcessingUrl("/doLogin")
//                        .failureUrl()
                        .successHandler((request, response, authentication) -> handlerWriteString(response, HttpServletResponse.SC_OK            , "登录成功！"))
                        .failureHandler((request, response, exception     ) -> handlerWriteString(response, HttpServletResponse.SC_NOT_ACCEPTABLE, "登录失败！"))
                        /*.addObjectPostProcessor(new ObjectPostProcessor<LoginUrlAuthenticationEntryPoint>() {
                            @Override
                            public <O extends LoginUrlAuthenticationEntryPoint> O postProcess(O object) {
                                object.setUseForward(true);
                                // 默认是redirect，ajax访问时会跨域
                                return object;
                            }
                        })*/)
        ;
    }

    private void handlerWriteString(HttpServletResponse response, int errCode, String errMsg) throws IOException {
        response.setContentType("application/json; charset=utf-8");
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
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }
}
