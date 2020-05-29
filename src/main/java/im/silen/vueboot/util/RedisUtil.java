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

    public static String lIndex(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    public static void lSet(String key, long index, String value) {
        redisTemplate.opsForList().set(key, index, value);
    }

    public static void lPush(String key, String... values) {
        redisTemplate.opsForList().leftPushAll(key, values);
    }
}
