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
public class VesperAutoConfiguration {

    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass(ObjectMapper.class)
    @AutoConfigureAfter({JacksonAutoConfiguration.class})
    static class JSONFactoryConfiguration {
        @Bean
        public JSONFactory jsonFactory(ObjectMapper objectMapper) {
            JSONFactory factory = new JSONFactory();

            factory.customize(objectMapper);
            return factory;
        }

    }

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
