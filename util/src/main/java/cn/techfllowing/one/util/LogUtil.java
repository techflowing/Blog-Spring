package cn.techfllowing.one.util;

public class LogUtil {

    public static void log(String msg) {
        System.out.println(DateUtil.getCurrentDateTime() + ": " + msg);
    }
}
