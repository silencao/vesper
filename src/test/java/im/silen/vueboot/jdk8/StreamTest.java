package im.silen.vueboot.jdk8;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import im.silen.vueboot.growth.Growth;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
public class StreamTest {
    private final String json = "[{\"level\":746,\"date\":\"2020-04-11T16:30:06.279Z\",\"sum\":\"5.3t\"},{\"date\":\"2020-04-13T16:19:55.634Z\",\"level\":748,\"sum\":\"6.8t\"},{\"date\":\"2020-04-19T15:44:28.301Z\",\"level\":750,\"sum\":\"11.4t\"},{\"date\":\"2020-04-23T16:01:00.802Z\",\"sum\":\"15.2t\",\"level\":754},{\"date\":\"2020-04-25T01:39:17.802Z\",\"level\":754,\"sum\":\"15.5t\"},{\"date\":\"2020-04-25T14:32:54.131Z\",\"level\":756,\"sum\":\"20.5t\"},{\"date\":\"2020-04-27T16:31:42.300Z\",\"level\":758,\"sum\":\"27.9t\"},{\"date\":\"2020-04-28T15:40:08.779Z\",\"sum\":\"32t\",\"level\":758}]";
    @Autowired
    private ObjectMapper mapper;
    private Stream<Growth> growthStream;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        growthStream = mapper.readValue(json, new TypeReference<List<HashMap<String, String>>>() {
        }).stream().map(o -> new Growth(o.get("sum"), ZonedDateTime.parse(o.get("date")).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime().toLocalDate(), Integer.parseInt(o.get("level"))));
    }

    @Test
    void collectTest() throws JsonProcessingException {
        List<Growth> list = growthStream.parallel().collect(() -> {
                    System.out.println("supplier");
                    return new ArrayList<>();
                },
                (growths, growth1) -> {
                    System.out.println("growth1 = " + growth1);
                    growths.add(growth1);
                },
                (growths, growths2) -> {
                    System.out.println("growths2 = " + growths2);
                    growths.addAll(growths2);
                });
        System.out.println(list);
        System.out.println(list.size());
    }

    @Test
    void reduceTest() {
        ArrayList<Growth> identity = new ArrayList<>();
        List<Growth> list = growthStream.parallel().reduce(identity, (growths, growth) -> {
            System.out.println("growth = " + growth);
            System.out.println(growths == identity);
            growths.add(growth);
            return growths;
        }, (growths, growths2) -> {
            System.out.println("growths2 = " + growths2);
            System.out.println("growths==growths2 = " + (growths == growths2));
            return growths;
        });
        System.out.println(list);
        System.out.println(list.size());
    }
}
