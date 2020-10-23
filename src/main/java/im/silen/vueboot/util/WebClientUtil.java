package im.silen.vueboot.util;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public class WebClientUtil {
    public static void post() {
        System.out.println(WebClient.create("http://localhost:8080")
                .get().accept(MediaType.ALL)
                .exchange()
                .flatMap(res -> res.bodyToMono(String.class)).block());
    }
}
