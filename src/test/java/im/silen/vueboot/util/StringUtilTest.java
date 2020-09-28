package im.silen.vueboot.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static im.silen.vueboot.util.StringUtil.stripWhitespace;

class StringUtilTest {
    @Test
    void stripWhitespace1() {
        Assertions.assertEquals(stripWhitespace(" \u202a   \nhello \n world   \n    "), "hello \n world");
        Assertions.assertEquals(stripWhitespace("hello world\t"), "hello world");
        Assertions.assertEquals(stripWhitespace("    \u202e\nhello\u202bworld"), "hello\u202bworld");
        Assertions.assertEquals(stripWhitespace("\n \t  \nhello world  !     \u202e\n    "), "hello world  !");
    }
}