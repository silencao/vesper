package util;

import java.util.Optional;

public class OptionalTest {
    private static boolean equalsTest(String str1, String str2) {
        return Optional.ofNullable(str1).equals(Optional.of(str2));
    }
    private static boolean equals(String str1, String str2){
        return Optional.ofNullable(str1).filter(str2::equals).isPresent();
    }

    public static void main(String[] args) {
        System.out.println("equalsTest(\"\", \"1\") = " + equalsTest("", "1"));
        System.out.println("equalsTest(null, \"1\") = " + equalsTest(null, "1"));
        System.out.println("equalsTest(\"\", \"\") = " + equalsTest("", ""));
        System.out.println("equalsTest(\"testStr\", \"testStr\") = " + equalsTest("testStr", "testStr"));

        System.out.println("equals(\"asd\", \"asd\") = " + equals("asd", "asd"));
        System.out.println("equals(\"t1\", \"\") = " + equals("t1", ""));
        System.out.println("equals(\"\", null) = " + equals(null, ""));
    }
}
