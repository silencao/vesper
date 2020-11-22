package im.silen.vueboot.java.util;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class MapTest {
    @Test
    void name() {
        Map<Integer, String> hashMap = new HashMap<>();
        hashMap.put(1, "one");
        hashMap.put(2, "two");
        System.out.println(hashMap.computeIfAbsent(3, integer -> {
            return "3";
        }));
        System.out.println(hashMap.computeIfPresent(3, (integer, s) -> {
            if ("4".equals(s))

                return "three";
            return "?";
        }));
        System.out.println(hashMap);
    }
}
