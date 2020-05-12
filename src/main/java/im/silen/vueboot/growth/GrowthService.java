package im.silen.vueboot.growth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import im.silen.vueboot.util.JSONArray;
import im.silen.vueboot.util.JSONObject;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class GrowthService {
    private String getRedisKey(String username) {
        return "growths:" + username;
    }

    private final StringRedisTemplate redisTemplate;

    public GrowthService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void add(String username, Growth growth) {

    }

    public List<Growth> search(String username){
       return Objects.requireNonNull(redisTemplate.opsForList().range(getRedisKey(username), 0, -1)).stream().map(s -> {
           try {
               return JSONObject.parse(s, Growth.class);
           } catch (JsonProcessingException e) {
               e.printStackTrace();
           }

           return null;
       }).collect(Collectors.toList());
    }
}
