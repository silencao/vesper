package im.silen.vesper.json;

import com.fasterxml.jackson.annotation.JsonFormat;
import im.silen.vesper.lib.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Map;

@SpringBootTest
class JsonTest {

    @Test
    void contextLoads() {
        Map<String, LocalDateTime> map = JSONObject.parse(
                "{\"datetime\":\"2020-04-28TEST15:40:08.779\"}"/*application.yml全局配置的格式*/,
                String.class,
                LocalDateTime.class);
        Item parse = JSONObject.parse("{\"datetime\":\"2020-04-28T15:40:08.779\"}", Item.class);

        System.out.println(JSONObject.stringify(map));
        System.out.println(JSONObject.stringify(parse));
    }

    private static class Item {
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS") // 可以覆盖application.yml文件中的配置
        private LocalDateTime datetime;

        public LocalDateTime getDatetime() {
            return datetime;
        }

        public void setDatetime(LocalDateTime datetime) {
            this.datetime = datetime;
        }
    }
}
