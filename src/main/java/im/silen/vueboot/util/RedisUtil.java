package im.silen.vueboot.util;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RedisUtil {
    private static StringRedisTemplate redisTemplate;

    private RedisUtil(StringRedisTemplate stringRedisTemplate) {
        RedisUtil.redisTemplate = stringRedisTemplate;
    }

    public static List<String> lRange(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    public static void lPush(String key, String value) {
        redisTemplate.opsForList().leftPush(key, value);
    }
}
