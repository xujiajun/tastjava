package cn.xujiajun.tastjava.util;

public class StrUtil {

    public static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }
}
