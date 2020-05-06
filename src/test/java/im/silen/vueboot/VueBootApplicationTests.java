package im.silen.vueboot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import im.silen.vueboot.growth.Growth;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

@SpringBootTest
class VueBootApplicationTests {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private ObjectMapper mapper;

    @Value("classpath:growths.json")
    private Resource resource;

    private <T> List<T> JSONparse1(String json, Class<T> clazz) throws JsonProcessingException {
        return mapper.readValue(json, new TypeReference<List<T>>() {
        });
    }
    private <T> List<T> JSONparse2(String json, Class<T> clazz) throws JsonProcessingException {
        return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
    }

    @Test
    void mapperReadTest() throws JsonProcessingException {
        TypeReference<List<Integer>> valueTypeRef = new TypeReference<List<Integer>>() {
        };

        System.out.println(valueTypeRef.getType());
        System.out.println(JSONparse1("[1,2,6,4,5]", Integer.class));
        String json = "[\n" +
                "  {\n" +
                "    \"level\": 746,\n" +
                "    \"date\": \"2020-04-11T16:30:06.279Z\",\n" +
                "    \"sum\": \"5.3t\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"date\": \"2020-04-13T16:19:55.634Z\",\n" +
                "    \"level\": 748,\n" +
                "    \"sum\": \"6.8t\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"date\": \"2020-04-19T15:44:28.301Z\",\n" +
                "    \"level\": 750,\n" +
                "    \"sum\": \"11.4t\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"date\": \"2020-04-23T16:01:00.802Z\",\n" +
                "    \"sum\": \"15.2t\",\n" +
                "    \"level\": 754\n" +
                "  },\n" +
                "  {\n" +
                "    \"date\": \"2020-04-25T01:39:17.802Z\",\n" +
                "    \"level\": 754,\n" +
                "    \"sum\": \"15.5t\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"date\": \"2020-04-25T14:32:54.131Z\",\n" +
                "    \"level\": 756,\n" +
                "    \"sum\": \"20.5t\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"date\": \"2020-04-27T16:31:42.300Z\",\n" +
                "    \"level\": 758,\n" +
                "    \"sum\": \"27.9t\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"date\": \"2020-04-28T15:40:08.779Z\",\n" +
                "    \"sum\": \"32t\",\n" +
                "    \"level\": 758\n" +
                "  },\n" +
                "  {\n" +
                "    \"date\": \"2020-04-30T15:39:08.599Z\",\n" +
                "    \"level\": 758,\n" +
                "    \"sum\": \"42.1t\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"date\": \"2020-05-02T13:23:42.270Z\",\n" +
                "    \"level\": 767,\n" +
                "    \"sum\": \"85.2t\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"date\": \"2020-05-04T05:34:16.267Z\",\n" +
                "    \"level\": 767,\n" +
                "    \"sum\": \"106.5t\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"date\": \"2020-05-04T14:03:22.733Z\",\n" +
                "    \"level\": 767,\n" +
                "    \"sum\": \"108.6t\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"date\": \"2020-05-05T11:15:42.141Z\",\n" +
                "    \"level\": 769,\n" +
                "    \"sum\": \"172.9t\"\n" +
                "  }\n" +
                "]";
        System.out.println(JSONparse1(json, Growth.class));
        System.out.println(JSONparse2(json, Growth.class));
        System.out.println(mapper.readValue(json, new TypeReference<List<Growth>>() {
        }));
        List<Growth> readValue = mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, Growth.class));
        System.out.println(readValue);
    }

    @Test
    void contextLoads() throws IOException {

        mapper.readValue(resource.getFile(), new TypeReference<List<HashMap<String, String>>>() {
        }).stream()
                .sorted(Comparator.comparing(o -> ZonedDateTime.parse(o.get("date"))))
                .peek(map -> System.out.println(ZonedDateTime.parse(map.get("date")).withZoneSameInstant(ZoneId.systemDefault())))
                .map(o -> new Growth(o.get("sum"), ZonedDateTime.parse(o.get("date")).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime().toLocalDate(), Integer.parseInt(o.get("level"))))
                .collect(LinkedHashMap<LocalDate, Growth>::new, (localDateGrowthHashMap, growth) -> {
                    System.out.println(growth);
                    localDateGrowthHashMap.put(growth.getDate(), growth);
                }, Map::putAll)
                .forEach((localDate, growth) -> {
                    try {
                        redisTemplate.opsForList().rightPush("growths:test", mapper.writeValueAsString(growth));
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                });

    }
}
