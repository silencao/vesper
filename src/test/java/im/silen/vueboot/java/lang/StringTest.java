package im.silen.vueboot.java.lang;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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

    private void genStr(String birthdayBegin, String birthdayEnd, String sex, String hBsAg, String comms, String moves, String stats, boolean isPrecheck, boolean isAdult) {
        System.out.println(
                "查询条件：受种者类别：" + (isAdult ? "成人" : "儿童") + "；" + (isPrecheck ? "今日问诊：√；" : "") + (
                        "".equals(birthdayBegin)
                                ? ""
                                : "出生日期：" + birthdayBegin + ("".equals(birthdayEnd) ? "" : " 至 " + birthdayEnd) + "；"
                ) + ("".equals(sex  ) ? "" : "性别："       + sex   + "；"
                ) + ("".equals(hBsAg) ? "" : "母HBsAg："    + hBsAg + "；"
                ) + ("".equals(comms) ? "" : "居委会："     + comms + "；"
                ) + ("".equals(moves) ? "" : "户籍类型："   + moves + "；"
                ) + ("".equals(stats) ? "" : "受种者状态：" + stats + "；"
                )
        );
    }

    @Test
    void test1() {
        genStr("2011-11-11", "", "男", "阴性", "", "", "", true, true);
        genStr("", "", "女", "", "", "", "", false, true);
        genStr("2011-11-11", "2011-12-01", "", "未检测", "", "", "", true, false);


    }

    @Test
    void test2() {
        System.out.println(Arrays.asList(1, 4, 3, 2, -5).stream()
                .sorted((x, y) -> Integer.compare(x, y)).collect(Collectors.toList()));
    }

    @Test
    void test3() {
        System.out.println(StringTest.class.getName());
    }

    public static String matchAndGet(String source, String regex) {
        Matcher matcher = Pattern.compile(regex).matcher(source);

        return matcher.find() ? matcher.group(1) : "";
    }
}
