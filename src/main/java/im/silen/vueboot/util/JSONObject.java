package im.silen.vueboot.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Component
public class JSONObject {
    private static final Log logger = LogFactory.getLog(JSONObject.class);
    private static ObjectMapper mapper;

    private JSONObject(ObjectMapper mapper) {
        JSONObject.mapper = mapper;
    }

    public static <T> T parse(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            logger.error("反序列化出错！", e);
        }
        throw new RuntimeException();
    }

    public static <K, V> Map<K, V> parse(String json, Class<K> keyClass, Class<V> valueClass) {
        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructMapType(Map.class, keyClass, valueClass));
        } catch (JsonProcessingException e) {
            logger.error("反序列化出错！", e);
        }
        throw new RuntimeException();
    }

    public static <T> T parse(File json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            logger.error("反序列化出错！", e);
        } catch (IOException e) {
            logger.error("未找到文件！", e);
        }
        throw new RuntimeException();
    }

    public static String stringify(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.error("序列化出错！", e);
            throw new RuntimeException();
        }
    }
}
