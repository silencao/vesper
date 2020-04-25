package im.silen.vueboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class VueBootApplicationTests {
@Autowired
    StringRedisTemplate redisTemplate;
    @Test
    void contextLoads() {
        System.out.println(redisTemplate.opsForList().range("growth:jinyan", 0, -1));
    }

}
