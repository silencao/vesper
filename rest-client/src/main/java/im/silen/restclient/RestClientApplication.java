package im.silen.restclient;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

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
        };
    };

}
