package im.silen.vueboot.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    public static String stripWhitespace(String input) {
        Pattern compile = Pattern.compile("[^\\h\\v\\u202a-\\u202e]+");
        int beginIndex = 0;
        int endIndex = 0;

        Matcher matcher = compile.matcher(input);
        while (matcher.find()) {
            beginIndex = endIndex > 0 ? beginIndex : matcher.start();
            endIndex = matcher.end();

        }

        return input.substring(beginIndex, endIndex);
    }
}
