package im.silen.vueboot.java.net.http;

import im.silen.restclient.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

@SpringBootTest
public class HttpClientTest {
    private CookieManager useCookieHandler() {
        return new CookieManager() {
            @Override
            public Map<String, List<String>> get(URI uri, Map<String, List<String>> requestHeaders) throws IOException {
                return Optional.of(super.get(uri, requestHeaders)
                ).map(headers -> getCookieStore().get(uri).stream()
                        .filter(cookie -> "XSRF-TOKEN".equals(cookie.getName()))
                        .findFirst().map(httpCookie -> {
                            Map<String, List<String>> newHeaders = new HashMap<>(headers);
                            newHeaders.put("X-XSRF-TOKEN", Collections.singletonList(httpCookie.getValue()));

                            return newHeaders;
                        }).orElse(headers)
                ).get();
            }
        };
    }

    @Test
    void loginSaaS() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
//                .cookieHandler(new CookieManager())
                .build();

        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder(URI.create("http://www.sssy.online:6681/inteLogin/epiMainProcess.action"));
        HttpResponse<String> response = httpClient.send(
                requestBuilder
                        .POST(HttpRequest.BodyPublishers.ofString("{\"macAddr\":\"\",\"form\":\"InteLogin.view.Main.Login\",\"func\":\"changeVerifyCode\",\"timeout\":30}"))
                        .build(),
                HttpResponse.BodyHandlers.ofString()
        );
        String verifyCode = JSONObject.parse(response.body(), String.class, String.class).get("verifyCode");

        Map<String, String> params = JSONObject.parse("{\"userCode\":\"3304214201super\",\"password\":\"ssgs1102\",\"stationCode\":\"3304214201\",\"verifyCode\":\"wjva\",\"macAddr\":\"\",\"form\":\"InteLogin.view.Main.Login\",\"func\":\"btnLogin\",\"timeout\":30}", String.class, String.class);

        params.put("verifyCode", verifyCode);
        HttpResponse<String> result = httpClient.send(requestBuilder
                .POST(HttpRequest.BodyPublishers.ofString(JSONObject.stringify(params)))
                .build(), HttpResponse.BodyHandlers.ofString());

        System.out.println("result.headers() = " + result.headers());
        System.out.println("result.body() = " + result.body());
    }

    @Test
    void login() throws IOException, InterruptedException {

        CookieManager cookieHandler = useCookieHandler();
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .cookieHandler(cookieHandler)
                .build();

        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder();

        URI uri = URI.create("http://localhost:8080/");
        HttpResponse<String> index = httpClient.send(requestBuilder
                .uri(uri)
                .GET().build(), HttpResponse.BodyHandlers.ofString());

        List<HttpCookie> cookies = cookieHandler.getCookieStore().get(uri);
        HttpRequest httpRequest = requestBuilder
                .uri(URI.create("http://localhost:8080/doLogin"))
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//                .setHeader("X-XSRF-TOKEN", cookies.stream()
//                        .filter(httpCookie -> "XSRF-TOKEN".equals(httpCookie.getName()))
//                        .map(HttpCookie::getValue)
//                        .findAny().orElseThrow())
                .POST(HttpRequest.BodyPublishers.ofString("username=admin&password=jiny&remember-me=true"))
                .build();

        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        System.out.println("response.body() = " + response.body());
        System.out.println("response.headers() = " + response.headers());
    }
}
