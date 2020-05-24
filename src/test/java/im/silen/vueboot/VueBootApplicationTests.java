package im.silen.vueboot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import im.silen.vueboot.growth.Growth;
import im.silen.vueboot.util.JSONArray;
import im.silen.vueboot.util.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
class VueBootApplicationTests {
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;
    @Autowired
    private ObjectMapper mapper;
    @Value("classpath:growths.json")
    private Resource resource;

    @Test
    void contextLoads() throws IOException {
        TypeFactory typeFactory = mapper.getTypeFactory();
        BoundListOperations<Object, Object> listOps = redisTemplate.boundListOps("growths:test");

        mapper.<List<HashMap<String, String>>>readValue(resource.getFile(), typeFactory.constructCollectionType(List.class, typeFactory.constructMapType(HashMap.class, String.class, String.class))).stream()
                .sorted(Comparator.comparing(o -> ZonedDateTime.parse(o.get("date"))))
                .peek(map -> System.out.println(ZonedDateTime.parse(map.get("date")).withZoneSameInstant(ZoneId.systemDefault())))
                .map(o -> new Growth(o.get("sum"), ZonedDateTime.parse(o.get("date")).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime().toLocalDate(), Integer.parseInt(o.get("level"))))
                .collect(LinkedHashMap<LocalDate, Growth>::new, (localDateGrowthHashMap, growth) -> {
                    System.out.println(growth);
                    localDateGrowthHashMap.put(growth.getDate(), growth);
                }, Map::putAll)
                .forEach((localDate, growth) -> {
                    try {
                        listOps.leftPush(JSONObject.stringify(growth));
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                });
    }
}
