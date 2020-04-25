package im.silen.vueboot.growth;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrowthService {
    private String getRedisKey(String username) {
        return "growth:" + username;
    }

    private final StringRedisTemplate redisTemplate;

    public GrowthService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void add(String username, Growth growth) {
//        redisTemplate.opsForList().leftPush(getRedisKey(username), growth);
    }

    public List<String> search(String username){
       return redisTemplate.opsForList().range(getRedisKey(username), 0,-1);
    }
}
