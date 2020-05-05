package im.silen.vueboot.growth;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/growth")
public class GrowthController {
    GrowthService growthService;

    public GrowthController(GrowthService growthService) {
        this.growthService = growthService;
    }

    @PostMapping("/{username}")
    public void create(@PathVariable String username) {
    }

    @DeleteMapping("/{username}")
    public void delete(@PathVariable String username) {
    }

    @PutMapping("/{username}")
    public void update(@PathVariable String username) {
    }

    @GetMapping
    public List<Growth> search() {
       return growthService.search("test");
    }
}
