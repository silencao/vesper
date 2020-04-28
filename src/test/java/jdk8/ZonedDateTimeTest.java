package jdk8;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ZonedDateTimeTest {

    private static ZoneId defaultZoneId = ZoneId.systemDefault();

    private static void logSameInstantLocalDateTime(String zonedDateTimeStr) {

        ZonedDateTime dateTime = ZonedDateTime.parse(zonedDateTimeStr);

        System.out.println(dateTime);
        System.out.println(dateTime.withZoneSameInstant(defaultZoneId).toLocalDateTime());
    }

    public static void main(String[] args) {
        logSameInstantLocalDateTime("2020-04-28T14:21:21.012Z");
        logSameInstantLocalDateTime("2020-04-27T16:31:42.300Z");

    }
}
