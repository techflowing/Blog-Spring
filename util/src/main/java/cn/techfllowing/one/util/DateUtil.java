package cn.techfllowing.one.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    /**
     * 格式化毫秒时间戳到日期 格式 yyyy-MM-dd
     *
     * @return 日期，格式 yyyy-MM-dd
     */
    public static String formatTimeToDate(long timestamp) {
        Date date = new Date(timestamp);
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    /**
     * 格式化毫秒时间戳到日期 格式 yyyy-MM-dd
     *
     * @return 日期，格式 yyyy-MM-dd
     */
    public static String getCurrentDateWithTime() {
        Date date = new Date(System.currentTimeMillis());
        return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(date);
    }

    /**
     * 获取当前日期+时间
     *
     * @return
     */
    public static String getCurrentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }

    /**
     * 获取秒级时间戳
     *
     * @param date 日期
     * @return
     */
    public static long getSecondTimestamp(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(date).getTime() / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
