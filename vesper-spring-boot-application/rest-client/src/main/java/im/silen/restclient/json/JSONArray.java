package im.silen.restclient.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JSONArray extends ArrayList<JSONObject> {
    private static final Log logger = LogFactory.getLog(JSONArray.class);
    public JSONArray() {
    }

    public static JSONArray parse(String json) {
        return JSONObject.parse(json, JSONArray.class);
    }

    public static <T> List<T> parse(String json, Class<T> clazz) {
        try {
            return JSONObject.mapper.readValue(json, JSONObject.mapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> parse(List<String> jsons, Class<T> clazz) {
        return jsons.stream().map(json -> JSONObject.parse(json, clazz)).collect(Collectors.toList());
    }
}
