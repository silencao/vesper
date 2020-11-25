package im.silen.vesper.lib.json;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONFactory {
    static ObjectMapper objectMapper;

    public void customize(ObjectMapper objectMapper) {
        JSONFactory.objectMapper = objectMapper;
    }
}
