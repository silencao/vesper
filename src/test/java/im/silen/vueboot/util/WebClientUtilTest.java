package im.silen.vueboot.util;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

class WebClientUtilTest {
    @Test
    void doPost() {

        WebClient webClient = WebClient.create("http://localhost:8080");
        Mono<String> voidFlux = webClient
                .get().uri("/")
                .accept(MediaType.TEXT_PLAIN)
                .retrieve()
                .bodyToMono(String.class)
                .map(s -> s);

        try {
            String body = voidFlux.block();
        } catch (WebClientResponseException e) {

            System.out.println(e.getResponseBodyAsString());
        }
        System.out.println("=========");
    }
}