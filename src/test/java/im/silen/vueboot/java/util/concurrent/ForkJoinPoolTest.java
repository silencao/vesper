package im.silen.vueboot.java.util.concurrent;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ForkJoinPoolTest {
    @Test
    void name() {
        LocalTime start = LocalTime.now();
        int parallelism = 100;

        System.out.println(Stream.generate(() -> {
            System.out.println(Thread.activeCount() + " => " + Thread.currentThread().getName());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return Math.random();
        }).parallel().limit(1000).collect(Collectors.summarizingDouble(value -> value)));

//        DoubleSummaryStatistics statistics = new ForkJoinPool(parallelism).submit(() -> DoubleStream.generate(() -> {
//            try {
////                System.out.println(Thread.activeCount() + " => " + Thread.currentThread().getName());
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            return Math.random();
//        }).parallel().limit(10_000).summaryStatistics()).get();

        LocalTime end = LocalTime.now();
        LocalTime useTime = LocalTime.ofNanoOfDay(start.until(end, ChronoUnit.NANOS));
        System.out.println(DateTimeFormatter.ofPattern("HH:mm:ss.SSS").format(useTime));
//        System.out.println(statistics);
//        System.out.println(statistics.getMax());
    }
}
