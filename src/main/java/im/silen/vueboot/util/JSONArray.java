package im.silen.vueboot.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class JSONArray {
    private static ObjectMapper mapper;

    private JSONArray(ObjectMapper mapper) {
        JSONArray.mapper = mapper;
    }

    public static <T> List<T> parse(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static <T> List<T> parse(List<String> jsons, Class<T> clazz) {
        return jsons.stream().map(json -> JSONObject.parse(json, clazz)).collect(Collectors.toList());
    }
}
