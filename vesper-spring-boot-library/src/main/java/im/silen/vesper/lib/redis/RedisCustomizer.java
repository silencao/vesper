package im.silen.vesper.lib.redis;

import org.springframework.data.redis.core.StringRedisTemplate;

public class RedisCustomizer {
    static StringRedisTemplate stringRedisTemplate;

    public void customize(StringRedisTemplate stringRedisTemplate) {
        RedisCustomizer.stringRedisTemplate = stringRedisTemplate;
    }
}
