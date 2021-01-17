package im.silen.vesper.json;

import com.fasterxml.jackson.annotation.JsonFormat;
import im.silen.vesper.lib.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class JsonTest {

    @Test
    void contextLoads() {
        Item parse = JSONObject.parse("{\"datetime\":\"2020-04-28T15:40:08.779\"}", Item.class);

        System.out.println(JSONObject.stringify(parse));
    }

    private static class Item {
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS") // 可以覆盖文件中的配置
        private LocalDateTime datetime;

        public LocalDateTime getDatetime() {
            return datetime;
        }

        public void setDatetime(LocalDateTime datetime) {
            this.datetime = datetime;
        }
    }
}
