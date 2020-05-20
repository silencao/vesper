package im.silen.vueboot.java.lang;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTest {
    public static void main(String[] args) {
        String s =
                        "2020-05-14 14:00:22       监管码：  1755 条，分别在   1 个门诊。\n" +
                        "2020-05-14 14:00:22     电子监管码   1654 条(+     0, *     0, -     0)\n";


        Matcher matcher = Pattern.compile("监管码：\\s+(\\d+)").matcher(s);
        Matcher matcher1 = Pattern.compile("电子监管码\\s+(\\d+)\\s条\\(\\+").matcher(s);

        while (matcher1.find()) {
            System.out.println(matcher1.group());
        }
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
        String[] strings = {
                matchAndGet(s, "监管码：\\s+(\\d+)"),
                matchAndGet(s, "电子监管码\\s+(\\d+)\\s条\\(\\+")
        };
        System.out.println(Arrays.asList(strings));
    }

    public static String matchAndGet(String source, String regex) {
        Matcher matcher = Pattern.compile(regex).matcher(source);

        return matcher.find() ? matcher.group(1) : "";
    }
}
