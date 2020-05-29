package im.silen.vueboot.growth;

import im.silen.vueboot.CurrentUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/growth")
public class GrowthController {
    private GrowthService growthService;

    public GrowthController(GrowthService growthService) {
        this.growthService = growthService;
    }

    @PostMapping
    public Growth create(
            @CurrentUser User user,
            @RequestBody Growth growth
            /*@RequestParam Optional<Integer> rp,
            @RequestHeader("X-XSRF-TOKEN") String token,
            @CookieValue("JSESSIONID") String cookie,
            @PathVariable String username*/) {
        return growthService.addOrSet(user.getUsername(), growth);
    }

    @DeleteMapping
    public void delete() {
    }

    @PutMapping
    public void update() {
    }

    @GetMapping
    public List<Growth> search(@CurrentUser User user) {
        return growthService.search("test");
    }
}
