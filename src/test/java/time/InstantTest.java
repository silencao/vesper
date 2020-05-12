package time;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class InstantTest {
    public static void main(String[] args) {
        Instant instant = Instant.parse("2020-05-11T07:00:16.853Z");

        System.out.println(instant);
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
        System.out.println(zonedDateTime);

        System.out.println(zonedDateTime.toEpochSecond());
    }
}
