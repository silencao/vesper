package im.silen.vueboot.util;

import im.silen.vesper.lib.json.JSONArray;
import im.silen.vueboot.growth.Growth;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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

    }
}
