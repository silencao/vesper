package im.silen.vueboot.growth;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/growth")
public class GrowthController {
    GrowthService growthService;

    public GrowthController(GrowthService growthService) {
        this.growthService = growthService;
    }

    @PostMapping("/{username}")
    public Growth create(
            @RequestParam Optional<Integer> rp,
            @RequestBody Growth growth,
            @RequestHeader("X-XSRF-TOKEN") String token,
            @CookieValue("JSESSIONID") String cookie,
            @PathVariable String username) {
        growth.setDate(LocalDate.now());
        return growth;
    }

    @DeleteMapping("/{username}")
    public void delete(@PathVariable String username) {
    }

    @PutMapping("/{username}")
    public void update(Model model, @PathVariable String username) {
    }

    @GetMapping
    public List<Growth> search(@AuthenticationPrincipal User user) {
        return growthService.search("test");
    }
}
