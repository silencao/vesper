package im.silen.vueboot.java.time;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class ClockTest {
    public static void main(String[] args) {
        Clock defClock = Clock.systemDefaultZone();
        Clock utcClock = Clock.systemUTC();
        System.out.println("defClock = " + defClock);
        System.out.println("utcClock = " + utcClock);
        System.out.println("System.currentTimeMillis() = " + System.currentTimeMillis());
        System.out.println("defClock.millis() = " + defClock.millis());
        System.out.println("utcClock.millis() = " + utcClock.millis());

        System.out.println("defClock.equals(utcClock) = " + defClock.equals(utcClock));
        System.out.println("Instant.now(utcClock) = " + Instant.now(utcClock));

        System.out.println("Instant.now().toEpochMilli() = " + Instant.now().toEpochMilli());
        System.out.println("LocalDateTime.now().until(LocalDate.now(), ChronoUnit.MINUTES) = " + LocalDate.now().until(LocalDate.of(2020, 5, 12), ChronoUnit.DAYS));
        System.out.println("LocalDate.from(LocalDateTime.now()) = " + LocalDate.from(LocalDateTime.now()));
        System.out.println("LocalDateTime.from(LocalDate.now()) = " + LocalDateTime.from(LocalDate.now()));
    }
}
