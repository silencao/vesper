package im.silen.vueboot.util;

import im.silen.vueboot.growth.Growth;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class RedisUtilTest {
    private static Log logger = LogFactory.getLog(RedisUtilTest.class);

    @Test
    void lRange() {
        List<String> list = RedisUtil.lRange("growths:test", 0, -1);
        list.forEach(logger::warn);
        JSONArray.parse(list, Growth.class).forEach(logger::info);
    }

    @Test
    void lPush() {
        RedisUtil.lPush("test", "1", "2", "3");
    }
}