package im.silen.vueboot.jdk8;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;

public class LocalDateTimeTest {
    private void test(String aStr) {
        Optional<String> o = Optional.ofNullable(aStr);

        o.ifPresent(System.out::println);
    }

    public static void main(String[] args) {

    }
}
