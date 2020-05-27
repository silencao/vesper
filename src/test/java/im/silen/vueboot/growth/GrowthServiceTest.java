package im.silen.vueboot.growth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import im.silen.vueboot.util.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GrowthServiceTest {
    @Autowired
    RedisTemplate<Object, Object> redisTemplate;
    @Autowired
    ObjectMapper objectMapper;
    @Value("classpath:growths.json")
    private Resource resource;
    @Test
    void add() throws IOException {
    }

    @Test
    void search() {

        BoundListOperations<Object, Object> listOps = redisTemplate.boundListOps("growths:test");
        listOps.range(0, -1).forEach(System.out::println);
        List<Object> g = redisTemplate.opsForList().range("g", 0, -1);
        System.out.println(g);
        g.stream().forEach(System.out::println);
    }
}