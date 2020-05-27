package im.silen.vueboot.growth;

import im.silen.vueboot.util.JSONArray;
import im.silen.vueboot.util.RedisUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrowthService {
    private String getRedisKey(String username) {
        return "growths:" + username;
    }

    List<Growth> search(String username){
       return JSONArray.parse(RedisUtil.lRange(getRedisKey(username), 0, -1), Growth.class);
    }
}
