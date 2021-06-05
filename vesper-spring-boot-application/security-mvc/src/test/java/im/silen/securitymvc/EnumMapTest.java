package im.silen.securitymvc;

import im.silen.vesper.lib.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EnumMapTest {
    enum ExceptionEnum {
        MissCsrfToken("mcf", "请求中缺少CsrfToken！");

        private final Object code; public Object getCode() { return code; }
        private final String text; public String getText() { return text; }

        ExceptionEnum(Object code, String text) {
            this.code = code;
            this.text = text;
        }

        public JSONObject toJSON() {
            return new JSONObject()
                    .fluentPut("code", code)
                    .fluentPut("text", text);
        }
    }

    @Test
    public void f1() {
        JSONObject jo1 = JSONObject.ofEnum(
                ExceptionEnum.class,
                ExceptionEnum::getCode,
                ExceptionEnum::getText
        );

        JSONObject jo2 = JSONObject.ofEnum(ExceptionEnum.class);
        System.out.println(ExceptionEnum.MissCsrfToken.toJSON());
        System.out.println(jo1);
        System.out.println(jo2);

    }
}
