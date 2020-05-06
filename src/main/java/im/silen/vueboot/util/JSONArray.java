package im.silen.vueboot.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JSONArray {
    private static ObjectMapper mapper;

    private JSONArray(ObjectMapper mapper) {
        JSONArray.mapper = mapper;
    }

    public static <T> List<T> parse(String json, Class<T> clazz) throws JsonProcessingException {
        return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
    }
}
