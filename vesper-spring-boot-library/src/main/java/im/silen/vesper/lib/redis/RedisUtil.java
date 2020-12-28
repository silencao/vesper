package im.silen.vesper.lib.redis;

import java.util.List;

import static im.silen.vesper.lib.redis.RedisCustomizer.stringRedisTemplate;

public class RedisUtil {
    public static List<String> lRange(String key, long start, long end) {
        return stringRedisTemplate.opsForList().range(key, start, end);
    }

    public static String lIndex(String key, long index) {
        return stringRedisTemplate.opsForList().index(key, index);
    }

    public static void lSet(String key, long index, String value) {
        stringRedisTemplate.opsForList().set(key, index, value);
    }

    public static void lPush(String key, String... values) {
        stringRedisTemplate.opsForList().leftPushAll(key, values);
    }
}
