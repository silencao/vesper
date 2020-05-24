package im.silen.vueboot.util;

import im.silen.vueboot.growth.Growth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisUtil {
    private static StringRedisTemplate stringRedisTemplatee;
    private static RedisTemplate redisTemplate;

    public RedisUtil(StringRedisTemplate stringRedisTemplate, RedisTemplate redisTemplate) {
        RedisUtil.stringRedisTemplatee = stringRedisTemplate;
        this.redisTemplate = redisTemplate;
    }


}
