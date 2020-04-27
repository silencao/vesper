package im.silen.vueboot;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootTest
class VueBootApplicationTests {
    @Autowired
    private ObjectMapper mapper;

    @Test
    void contextLoads() throws JsonProcessingException {
        LocalTime time = LocalTime.of(2, 30);
        System.out.println(time.toString());
        CS x = mapper.readValue("{\"date\":\"2011?11?11\",\"time\":\"1230\"}", CS.class);
        System.out.println(x.toString());
        System.out.println(mapper.writeValueAsString(new CS(LocalDate.now(), time)));
    }
    static class CS {
        @JsonFormat(pattern = "yyyy?MM?dd")
        LocalDate date;
        @JsonFormat(pattern = "HHmm")
        LocalTime time;

        public CS() {
        }

        public CS(LocalDate date, LocalTime time) {
            this.date = date;
            this.time = time;
        }

        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public LocalTime getTime() {
            return time;
        }

        public void setTime(LocalTime time) {
            this.time = time;
        }

        @Override
        public String toString() {
            return "CS{" +
                    "date=" + date +
                    ", time=" + time +
                    '}';
        }
    }

}
