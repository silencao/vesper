package im.silen.fluxserver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {
    @GetMapping("/hello")
    public String hello() throws InterruptedException {
        Thread.sleep(5000);

        return "Hello World!";
    }

}
