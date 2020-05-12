package time;

import java.time.Clock;
import java.time.LocalDateTime;

public class ClockTest {
    public static void main(String[] args) {
        Clock clock = Clock.systemDefaultZone();
        System.out.println(clock.millis());
        System.out.println(LocalDateTime.now());
    }
}
