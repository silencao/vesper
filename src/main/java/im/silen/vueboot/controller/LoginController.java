package im.silen.vueboot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
    @RequestMapping("/needLogin")
    public ResponseEntity<String> login() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("需要登录");
    }

    @PostMapping("/success")
    public String success() {
        return "登录成功";
    }

    @PostMapping("/failure")
    public String failure() {
        return "登录失败";
    }
}
