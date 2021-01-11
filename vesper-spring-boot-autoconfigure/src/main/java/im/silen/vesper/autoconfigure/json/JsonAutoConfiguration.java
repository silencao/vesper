package im.silen.vesper.autoconfigure.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import im.silen.vesper.lib.json.JSONFactory;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(ObjectMapper.class)
@AutoConfigureBefore(JacksonAutoConfiguration.class)
//@AutoConfigureAfter(JacksonAutoConfiguration.class)
@EnableConfigurationProperties(JsonFormatProperties.class)
public class JsonAutoConfiguration {
    @Bean
    public JSONFactory jsonFactory(ObjectMapper objectMapper) {
        JSONFactory factory = new JSONFactory();

        factory.customize(objectMapper);
        return factory;
    }

    /**
     * 不创建自动配置里的objectMapper，需要改写java8时间api的序列化
     * @param objectMapperBuilder 自动配置中的builder
     * @return ioc容器管理的objectMapper
     */
    @Bean
    ObjectMapper objectMapper(Jackson2ObjectMapperBuilder objectMapperBuilder, JsonFormatProperties jsonFormatProperties) {
        return Optional.ofNullable(jsonFormatProperties.getLocalDateTime())
                .map(DateTimeFormatter::ofPattern)
                .map(formatter -> objectMapperBuilder.modules(new Jdk8Module(), new JavaTimeModule()
                        .addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(formatter))
                        .addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(formatter))
                )).orElse(objectMapperBuilder)
                .createXmlMapper(false)
                .build();
    }
}
