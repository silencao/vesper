package http;

import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.util.function.Consumer;
import java.util.stream.IntStream;

public class HttpClientDemo {

    private static final String uri = "http://localhost:8080/hello";

    public static void main(String[] args) throws InterruptedException {
        HttpClient client = HttpClient.create();
        IntStream.rangeClosed(1, 1000).forEach(i -> {

            Mono<String> stringMono = client.get()
                    .uri(uri)
                    .responseContent()
                    .aggregate()
                    .asString();

            Consumer<String> prinf = s -> System.out.printf("%2s, %s%n", i, s);
            stringMono.subscribe(prinf);
//            stringMono.blockOptional().ifPresent(prinf);
        });

        Thread.sleep(100000);
    }
}
