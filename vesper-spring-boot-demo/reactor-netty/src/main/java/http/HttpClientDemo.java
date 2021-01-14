package http;

import reactor.netty.http.client.HttpClient;

public class HttpClientDemo {

    private static final String uri = "http://www.sssy.online:6681/InteLogin/index.html";

    public static void main(String[] args) {
        HttpClient client = HttpClient.create();

        String block = client.get()
                .uri(uri)
                .responseContent()
                .aggregate()
                .asString()
                .block();

        System.out.println(1);
    }
}
