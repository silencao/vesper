package im.silen.vesper.lib.json;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static im.silen.vesper.lib.json.JSONFactory.objectMapper;

public class JSONObject extends HashMap<String, Object> {
    public JSONObject fluentPut(String key, Object value) {
        super.put(key, value);

        return this;
    }

    public static <K, V> Map<K, V> parse(String json, Class<K> keyClass, Class<V> valueClass) {
        try {
            return objectMapper.readValue(json, objectMapper.getTypeFactory().constructMapType(Map.class, keyClass, valueClass));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static JSONObject parse(String json) { return parse(json, JSONObject.class); }
    public static JSONObject parse(File   file) { return parse(file, JSONObject.class); }

    public static <T> T parse(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T parse(File file, Class<T> clazz) {
        try {
            return objectMapper.readValue(file, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String stringify(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
