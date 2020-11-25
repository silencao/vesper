package im.silen.vueboot;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import im.silen.vesper.lib.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@SpringBootTest
public class ObjectMapperTest {
    @Autowired
    private ObjectMapper beanMapper;
    private final ObjectMapper anewMapper = new ObjectMapper();
    private final LocalDate date = LocalDate.now();

    private final LocalTime time = LocalTime.now();
    private final LocalDateTime dateTime = LocalDateTime.of(date, time);
    private final Item item = new Item(date, time, dateTime);

    @Test
    void writeValueAsString() throws JsonProcessingException {
        System.out.println(JSONObject.stringify(item));
        System.out.println("anewMapper.writeValueAsString(item) = " + anewMapper.writeValueAsString(item));
        System.out.println("beanMapper.writeValueAsString(item) = " + beanMapper.writeValueAsString(item));
    }

    @Test
    void readValue() throws JsonProcessingException {
        String src = "{\"date\":\"2011--02--11\",\"time\":\"14T33T55\",\"dateTime\":\"2020?01?01 14???33:55\"}";

        Item item = beanMapper.readValue(src, Item.class);
        System.out.println("beanMapper.readValue(src, Item.class) = " + item);
        System.out.println("item.getDate() = " + item.getDate());
        System.out.println("item.getTime() = " + item.getTime());
        System.out.println("item.getDateTime() = " + item.getDateTime());

        try { // 下面的会报错
            System.out.println("anewMapper.readValue(src, Item.class) = " + anewMapper.readValue(src, Item.class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private static class Item{
        @JsonFormat(pattern = "yyyy--MM--dd")
        private LocalDate       date;
        @JsonFormat(pattern = "HH'T'mm'T'ss")
        private LocalTime       time;
        @JsonFormat(pattern = "yyyy?MM?dd HH???mm:ss")
        private LocalDateTime   dateTime;

        @SuppressWarnings("unused") // 不写空参构造器 readValue 反序列化会报错
        public Item() {
        }

        Item(LocalDate date, LocalTime time, LocalDateTime dateTime) {
            this.date = date;
            this.time = time;
            this.dateTime = dateTime;
        }

        LocalDate getDate() {
            return date;
        }

        LocalTime getTime() {
            return time;
        }

        LocalDateTime getDateTime() {
            return dateTime;
        }
    }
}









