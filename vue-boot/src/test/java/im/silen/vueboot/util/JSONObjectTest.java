package im.silen.vueboot.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import im.silen.vueboot.growth.Growth;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//@SpringBootTest
class JSONObjectTest {
    @Value("classpath:growths.json")
    Resource resource;

    @Test
    void name() {
        IntStream.rangeClosed(10, 20).mapToDouble(value -> value).forEach(value -> {
            System.out.printf(value + " / 3 = " + value / 3 + "    ");
            System.out.println(String.valueOf(Math.ceil(value / 3)));
        });
    }

    @Test
    void bean() throws JsonProcessingException {
        JSONObject jsonObject = JSONObject.parse("{\n" +
                "    \"user\": \"cjy\",\n" +
                "    \"age\": 13,\n" +
                "    \"debug\": true\n" +
                "  }");

        JSONArray users = JSONArray.parse("[\n" +
                "  {\n" +
                "    \"user\": \"cjy\",\n" +
                "    \"age\": 13,\n" +
                "    \"debug\": true\n" +
                "  },\n" +
                "  {\n" +
                "    \"user\": \"sxw\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"user\": \"wz\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"user\": \"zfx\"\n" +
                "  }\n" +
                "]");

        List<Integer> integers = IntStream.range(1, 10).boxed().collect(Collectors.toList());

        Map<Object, List<JSONObject>> collect = users.stream().collect(Collectors.groupingBy(jo -> {
            return 1;
        }, Collectors.toList()));

        Map<JSONObject, List<Integer>> paper = paper(users, integers);
        System.out.println("========");
    }
    
    private <K, V> Map<K, List<V>> paper(List<K> keys, List<V> values) {
        int pageSize = (int) Math.ceil((double) values.size() / keys.size());

        AtomicInteger idx = new AtomicInteger(0);
        return values.stream().collect(Collectors.groupingBy(v -> keys.get(idx.getAndIncrement() / pageSize)));
    }

    @Test
    void parse() throws IOException {

        try (InputStream stream = resource.getInputStream();
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