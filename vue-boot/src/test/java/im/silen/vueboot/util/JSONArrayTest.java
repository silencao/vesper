package im.silen.vueboot.util;

import im.silen.vueboot.growth.Growth;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
class JSONArrayTest {

    @Test
    void parse() {
        JSONArray.parse("[\n" +
                "  {\n" +
                "    \"level\": 746,\n" +
                "    \"date\": \"2020-04-11T16:30:06.279Z\",\n" +
                "    \"sum\": \"5.3t\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"date\": \"2020-04-13T16:19:55.634Z\",\n" +
                "    \"level\": 748,\n" +
                "    \"sum\": \"6.8t\"\n" +
                "  }\n" +
                "]", Growth.class).forEach(System.out::println);

        JSONArray.parse(Arrays.asList(
                "{\"date\": \"2020-04-19T15:44:28.301Z\",\"level\": 750,\"sum\": \"11.4t\"}",
                "{\"date\": \"2020-04-25T01:39:17.802Z\", \"level\": 754, \"sum\": \"15.5t\"}",
                "{\"date\": \"2020-04-25T14:32:54.131Z\", \"level\": 756, \"sum\": \"20.5t\"}"
        ), Growth.class).forEach(System.out::println);
    }
}
