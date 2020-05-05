package im.silen.vueboot.growth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GrowthService {
    private String getRedisKey(String username) {
        return "growths:" + username;
    }
private ObjectMapper objectMapper;
    private final StringRedisTemplate redisTemplate;

    public GrowthService(StringRedisTemplate redisTemplate, ObjectMapper objectMapper, ObjectMapper objectMapper1) {
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper1;
    }

    public void add(String username, Growth growth) {
//        redisTemplate.opsForList().leftPush(getRedisKey(username), growth);
    }

    public List<Growth> search(String username){
       return redisTemplate.opsForList().range(getRedisKey(username), 0,-1).stream().map(s -> {
           try {
               return objectMapper.readValue(s,Growth.class);
           } catch (JsonProcessingException e) {
               e.printStackTrace();
           }
           return null;
       }).collect(Collectors.toList());
    }
}
