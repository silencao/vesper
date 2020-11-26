package im.silen.restclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import im.silen.vesper.lib.json.JSONFactory;
import im.silen.vesper.lib.json.JSONObject;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.Collections;

@SpringBootApplication
public class RestClientApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(RestClientApplication.class);
        springApplication.setWebApplicationType(WebApplicationType.NONE);
        springApplication.run(args);
    }

    @Bean
    CommandLineRunner lineRunner(WebClient.Builder builder) {
        return args -> {
            System.out.println(builder.baseUrl("https://spring.io/projects").build()
                    .get()
                    .retrieve().bodyToMono(String.class).block());

            System.out.println("测试是否注入了自动配置");
            System.out.println(JSONObject.stringify(Collections.singletonMap("datetime", LocalDateTime.now())));
        };
    };

}
