package im.silen.vesper.autoconfigure;

import im.silen.vesper.lib.redis.RedisCustomizer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration(proxyBeanMethods = false)
public class VesperAutoConfiguration {

    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass(StringRedisTemplate.class)
    static class RedisCustomizerConfiguration {
        @Bean
        public RedisCustomizer redisCustomizer(StringRedisTemplate stringRedisTemplate) {
            RedisCustomizer customizer = new RedisCustomizer();

            customizer.customize(stringRedisTemplate);
            return customizer;
        }
    }
}
