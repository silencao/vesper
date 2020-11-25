package im.silen.vueboot.util;

import im.silen.vesper.lib.json.JSONArray;
import im.silen.vueboot.growth.Growth;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.function.Consumer;

@SpringBootTest
class RedisUtilTest {

    @Test
    void lRange() {
        List<String> list = RedisUtil.lRange("growths:test", 0, -1);
        Consumer<Object> println = System.out::println;
        list.forEach(println);
        JSONArray.parse(list, Growth.class).forEach(println);
    }
}