package im.silen.vueboot.growth;

import im.silen.vesper.lib.json.JSONArray;
import im.silen.vesper.lib.json.JSONObject;
import im.silen.vueboot.util.RedisUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class GrowthService {
    private String by(String username) {
        return "growths:" + username;
    }

    Growth addOrSet(String username, Growth growth) {
        String key = by(username);
        LocalDate now = LocalDate.now();
        Growth data = JSONObject.parse(RedisUtil.lIndex(key, -1), Growth.class);
        growth.setDate(now);

        String json = JSONObject.stringify(growth);
        if (now.equals(data.getDate())) {
            RedisUtil.lSet(key, -1, json);
        } else {
            RedisUtil.lPush(key, json);
        }
        return growth;
    }

    List<Growth> search(String username) {
        return JSONArray.parse(RedisUtil.lRange(by(username), -10, -1), Growth.class);
    }
}
