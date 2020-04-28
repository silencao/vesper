package im.silen.vueboot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class VueBootApplicationTests {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private ObjectMapper mapper;

    @Test
    void contextLoads() throws JsonProcessingException {
        String json = "[{\"level\":746,\"date\":\"2020-04-11T16:30:06.279Z\",\"sum\":\"5.3t\"},{\"date\":\"2020-04-13T16:19:55.634Z\",\"level\":748,\"sum\":\"6.8t\"},{\"date\":\"2020-04-19T15:44:28.301Z\",\"level\":750,\"sum\":\"11.4t\"},{\"date\":\"2020-04-23T16:01:00.802Z\",\"sum\":\"15.2t\",\"level\":754},{\"date\":\"2020-04-25T01:39:17.802Z\",\"level\":754,\"sum\":\"15.5t\"},{\"date\":\"2020-04-25T14:32:54.131Z\",\"level\":756,\"sum\":\"20.5t\"},{\"date\":\"2020-04-27T16:31:42.300Z\",\"level\":758,\"sum\":\"27.9t\"},{\"date\":\"2020-04-28T15:40:08.779Z\",\"sum\":\"32t\",\"level\":758}]";
        mapper.readValue(json, new TypeReference<List<HashMap<String, String>>>() {
        }).forEach(o -> {
            System.out.println(o);
            System.out.println(ZonedDateTime.parse(o.get("date")).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime());
        });
    }
}
