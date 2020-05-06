package im.silen.vueboot.jdk8;

import im.silen.vueboot.growth.Growth;

public class ReflectTest {
    private static <T> void getName(T clazz) {
        System.out.println(clazz);

    }

    public static void main(String[] args) {
        getName(Growth.class);

    }
}
