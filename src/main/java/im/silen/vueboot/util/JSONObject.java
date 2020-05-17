package im.silen.vueboot.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class JSONObject {
    private static ObjectMapper mapper;

    private JSONObject(ObjectMapper mapper) {
        JSONObject.mapper = mapper;
    }

    public static <T> T parse(String json, Class<T> clazz) throws JsonProcessingException {
        return mapper.readValue(json, clazz);
    }

    public static String stringify(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }
}
