package im.silen.restclient.growth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import im.silen.vesper.lib.json.JSONArray;
import im.silen.vesper.lib.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@SpringBootTest
public class GrowthTest {
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void name() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:im/silen/restclient/growth/data/growth.json");

        Growth growth1 = JSONObject.parse(file, Growth.class);
        Growth growth2 = JSONObject.parse("{\"sum\":\"89.4x\",\"date\":\"2021-12-26\",\"level\":13}", Growth.class);

        System.out.println(growth1);
        System.out.println(growth2);

        ObjectReader objectReader = objectMapper.readerFor(Growth.class);
        try {
            System.out.println(objectReader.<Growth>readValue(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            File src = ResourceUtils.getFile("classpath:im/silen/restclient/growth/data/growths.json");
            String json = "[\n" +
                    "  {\"date\": \"2020-04-19T15:44:28.301Z\", \"level\": 750, \"sum\": \"11.4t\"},\n" +
                    "  {\"date\": \"2020-04-25T01:39:17.802Z\", \"level\": 754, \"sum\": \"15.5t\"},\n" +
                    "  {\"date\": \"2020-04-25T14:32:54.131Z\", \"level\": 756, \"sum\": \"20.5t\"} \n" +
                    "]";
            List<Growth> growths1 = JSONArray.parse(json, Growth.class);
            List<Growth> growths2 = objectMapper.readerFor(Growth.class).<Growth>readValues(src).readAll();
            List<Growth> growths3 = objectMapper.readerForListOf(Growth.class).readValue(json);
            List<Growth> growths4 = JSONArray.parse(List.of(
                    "{\"date\": \"2020-04-19T15:44:28.301Z\", \"level\": 750, \"sum\": \"11.4t\"}",
                    "{\"date\": \"2020-04-25T14:32:54.131Z\", \"level\": 756, \"sum\": \"20.5t\"}"
            ), Growth.class);

            System.out.println(JSONArray.stringify(growths1));
            System.out.println(JSONArray.stringify(growths2));
            System.out.println(JSONArray.stringify(growths3));
            System.out.println(JSONArray.stringify(growths4));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
