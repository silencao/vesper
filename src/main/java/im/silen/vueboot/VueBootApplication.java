package im.silen.vueboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

@SpringBootApplication
@Controller
public class VueBootApplication {
//    @GetMapping("/")
//    private String index(){
//        return "login";
//    }
    public static void main(String[] args) {
        SpringApplication.run(VueBootApplication.class, args);
    }

}
