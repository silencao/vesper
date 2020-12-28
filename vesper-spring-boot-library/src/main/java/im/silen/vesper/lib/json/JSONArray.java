package im.silen.vesper.lib.json;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static im.silen.vesper.lib.json.JSONFactory.objectMapper;

public class JSONArray extends ArrayList<JSONObject> {
    public static JSONArray parse(String json) {
        return JSONObject.parse(json, JSONArray.class);
    }

    public static <T> List<T> parse(String json, Class<T> clazz) {
        try {
            return objectMapper.readerForListOf(clazz).readValue(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> parse(List<String> jsons, Class<T> clazz) {
        return parse(jsons.stream().collect(Collectors.joining(",", "[", "]")), clazz);
    }

    public static String stringify(Object object) {
        return JSONObject.stringify(object);
    }
}
