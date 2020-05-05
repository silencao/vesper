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
import java.util.stream.Stream;

@SpringBootTest
class VueBootApplicationTests {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private ObjectMapper mapper;

    @Value("classpath:growths.json")
    private Resource resource;

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
