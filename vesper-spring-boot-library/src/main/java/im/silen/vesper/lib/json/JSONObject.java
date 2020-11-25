package im.silen.vesper.lib.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static im.silen.vesper.lib.json.JSONFactory.objectMapper;

public class JSONObject extends HashMap<String, Object> {
    private static final Log logger = LogFactory.getLog(JSONObject.class);

    @Override
    public JSONObject put(String key, Object value) {
        super.put(key, value);

        return this;
    }

    public static JSONObject parse(String json) {
        return parse(json, JSONObject.class);
    }

    public static <T> T parse(String json, Class<T> clazz) {
        try {

            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            logger.error("反序列化出错！", e);
        }
        throw new RuntimeException();
    }

    public static <K, V> Map<K, V> parse(String json, Class<K> keyClass, Class<V> valueClass) {
        try {
            return objectMapper.readValue(json, objectMapper.getTypeFactory().constructMapType(Map.class, keyClass, valueClass));
        } catch (JsonProcessingException e) {
            logger.error("反序列化出错！", e);
        }
        throw new RuntimeException();
    }

    public static <T> T parse(File json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            logger.error("反序列化出错！", e);
        } catch (IOException e) {
            logger.error("未找到文件！", e);
        }
        throw new RuntimeException();
    }

    public static String stringify(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.error("序列化出错！", e);
            throw new RuntimeException();
        }
    }
}
