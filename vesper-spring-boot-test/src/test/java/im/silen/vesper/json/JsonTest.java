package im.silen.vesper.json;

import im.silen.vesper.lib.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@SpringBootTest
class JsonTest {

    @Test
    void contextLoads() {
        Item parse = JSONObject.parse("{\"datetime\":\"2020-04-28T15:40:08.779Z\"}", Item.class);

        Instant now = Instant.now();
        System.out.println(LocalDateTime.ofInstant(now, ZoneId.systemDefault()));
        System.out.println(now);
        System.out.println(JSONObject.stringify(parse));
    }

    private static class Item {
//        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
        private LocalDateTime datetime;

        public Item() {
        }

        public LocalDateTime getDatetime() {
            return datetime;
        }

        public void setDatetime(Instant instant) {
            this.datetime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        }
    }
}
