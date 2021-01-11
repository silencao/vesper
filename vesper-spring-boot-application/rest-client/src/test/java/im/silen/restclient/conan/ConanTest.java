package im.silen.restclient.conan;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import im.silen.vesper.lib.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.util.ResourceUtils;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootTest
public class ConanTest {
    @Test
    void name(@Autowired ObjectMapper objectMapper) throws FileNotFoundException {
        File f1 = ResourceUtils.getFile("classpath:growth.json");
        objectMapper.copy();
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Test
    void csv() {
        WebClient.create("https://www.sbsub.com/data/assets/data.json")
                .get().accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(String.class)
                .blockOptional()
                .ifPresent(data -> {

                    List res = (List) JSONObject.parse(data).get("res");
                    List  tv = (List) res.get(0);
                    Map<String, List> listMap = (Map<String, List>) tv.get(4);

                    List<Conan> collect = listMap.values().stream().map(list -> new Conan(
                            (String) list.get(0),
                            (String) list.get(1),
                            String.join(",", ((Map<String, Object>) list.get(6)).keySet())
                    )).collect(Collectors.toList());

                    CsvMapper csvMapper = new CsvMapper();

                    try {
                        csvMapper.writerWithSchemaFor(Conan.class).writeValues(new File("c:/temp/test.csv")).writeAll(collect);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

    }
}
