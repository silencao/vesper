package im.silen.vueboot;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<String> handler(MissingServletRequestParameterException e) {
        return ResponseEntity.badRequest().body("内部错误：" + e.getParameterType() + " '" + e.getParameterName() + "' 参数无效！");
    }
}
