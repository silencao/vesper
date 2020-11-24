package im.silen.vueboot.util;

import im.silen.vueboot.csrf.CsrfRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class WebClientUtilTest {
    @Autowired
    private WebClient.Builder webClientBuilder;

    private<R, T> Collector<DoubleSummaryStatistics, List<String>, List<String>> pageWith(int size, Function<DoubleSummaryStatistics, String> getter, Consumer<List<String>> handleData) {

        return Collector.of(() -> new ArrayList<>(size), (objects, o) -> {
            objects.add(getter.apply(o));

            if (objects.size() == size)
                handleData.accept(objects);

        }, (left, right) -> {
            left.addAll(right);

            return left;
        });
    }
    @Test
    void name() {
        List<String> collect = Stream.generate(() -> DoubleStream.generate(Math::random).limit(200).summaryStatistics())
                .limit(20)
                .collect(pageWith(4, DoubleSummaryStatistics::toString, strings -> {
                    strings.forEach(System.out::println);
                }));

        System.out.println(collect.size());

    }

    @Test
    void doPost() throws InterruptedException {
        WebClient httpClient = Mono.just(Optional.ofNullable(webClientBuilder)
                .orElseGet(WebClient::builder)
                .baseUrl("http://127.0.0.1:8080")
                .build()
        ).flatMap(webClient -> webClient.get().uri("/")
                .accept(MediaType.TEXT_PLAIN)
                .exchange().map(clientResponse -> {
                    if (clientResponse.statusCode() == HttpStatus.UNAUTHORIZED) {
                        WebClient.Builder mutate = webClient.mutate();
                        clientResponse.cookies().forEach((key, responseCookies) -> {
                            String[] values = responseCookies.stream()
                                    .map(HttpCookie::getValue).toArray(String[]::new);

                            if (CsrfRepository.DEFAULT_CSRF_COOKIE_NAME.equals(key)) {
                                mutate.defaultHeader(CsrfRepository.DEFAULT_CSRF_HEADER_NAME, values);
                            }

                            mutate.defaultCookie(key, values);

                        });

                        return mutate.build();
                    } else {
                        return webClient;
                    }
                })
        ).blockOptional().orElseThrow();

        String body = httpClient.post().uri("/doLogin")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .bodyValue("username=admin&password=jiny")
                .retrieve()
                .bodyToMono(String.class)
                .blockOptional().orElseThrow();

        System.out.println("=========" + body);

    }

}