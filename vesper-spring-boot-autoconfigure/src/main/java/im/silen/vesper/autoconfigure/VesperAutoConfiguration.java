package im.silen.vesper.autoconfigure;

import com.fasterxml.jackson.databind.ObjectMapper;
import im.silen.vesper.lib.json.JSONFactory;
import im.silen.vesper.lib.redis.RedisCustomizer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration(proxyBeanMethods = false)
@ConditionalOnClass
@AutoConfigureAfter({JacksonAutoConfiguration.class})
public class VesperAutoConfiguration {
    @Bean
    public JSONFactory jsonFactory(ObjectMapper objectMapper) {
        JSONFactory factory = new JSONFactory();

        factory.customize(objectMapper);
        return factory;
    }

    @Bean
    @ConditionalOnClass({StringRedisTemplate.class})
    public RedisCustomizer redisCustomizer(StringRedisTemplate stringRedisTemplate) {
        RedisCustomizer customizer = new RedisCustomizer();

        customizer.customize(stringRedisTemplate);
        return customizer;
    }
}
