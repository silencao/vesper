package im.silen.vueboot.util;

import im.silen.vueboot.growth.Growth;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

@SpringBootTest
class JSONObjectTest {
    @Value("classpath:growths.json")
    Resource resource;

    @Test
    void parse() throws IOException {

        try (
                InputStream stream = resource.getInputStream();
                InputStreamReader streamReader = new InputStreamReader(stream);
                BufferedReader bufferedReader = new BufferedReader(streamReader);
        ) {
           bufferedReader.lines().forEach(System.out::println);
        }

        System.out.println("resource.getURI() = " + resource.getURI());
        System.out.println("resource.getURL() = " + resource.getURL());
        System.out.println(JSONObject.parse("{\"date\": \"2020-04-19T15:44:28.301Z\", \"level\": 750, \"sum\": \"11.4t\"}", Growth.class));
        Map<String, String> parse = JSONObject.parse("{\"date\": \"2020-04-19T15:44:28.301Z\", \"level\": 750, \"sum\": \"11.4t\"}", String.class, String.class);
        System.out.println(parse.get("date"));

        System.out.println(JSONObject.parse("{\"date\": \"12020-04-19T15:44:28.301Z\", \"level\": 750, \"sum\": \"11.4t\"},", Growth.class));
    }

    @Test
    void stringify() {

        throw new RuntimeException();
    }
}