package im.silen.vueboot;

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
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

        Stream<Growth> growthStream = mapper.readValue(resource.getFile(), new TypeReference<List<HashMap<String, String>>>() {
        }).stream().sorted(Comparator.comparing(o -> ZonedDateTime.parse(o.get("date")))).map(o -> new Growth(o.get("sum"), ZonedDateTime.parse(o.get("date")).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime().toLocalDate(), Integer.parseInt(o.get("level"))));
        Map<LocalDate, Growth> map = growthStream.collect(HashMap::new,(localDateGrowthHashMap, growth) -> {
            localDateGrowthHashMap.put(growth.getDate(),growth);
        },HashMap::putAll);
        System.out.println(map);
        System.out.println(map.size());
    }
}
