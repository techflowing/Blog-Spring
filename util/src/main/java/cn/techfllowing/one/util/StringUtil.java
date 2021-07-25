package cn.techfllowing.one.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符创工具类
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2020/11/4 5:03 下午
 */
public class StringUtil {

    /**
     * Returns true if the string is null or 0-length.
     *
     * @param str the string to be examined
     * @return true if str is null or zero length
     */
    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }

    /**
     * Returns true if a and b are equal, including if they are both null.
     * <p><i>Note: In platform versions 1.1 and earlier, this method only worked well if
     * both the arguments were instances of String.</i></p>
     *
     * @param a first CharSequence to check
     * @param b second CharSequence to check
     * @return true if a and b are equal
     */
    public static boolean equals(CharSequence a, CharSequence b) {
        if (a == b) return true;
        int length;
        if (a != null && b != null && (length = a.length()) == b.length()) {
            if (a instanceof String && b instanceof String) {
                return a.equals(b);
            } else {
                for (int i = 0; i < length; i++) {
                    if (a.charAt(i) != b.charAt(i)) return false;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 根据正则表达式判断是否匹配字符串
     *
     * @param content 字符串
     * @param regex   正则表达式
     */
    public static boolean isStringMatch(String content, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        return matcher.find();
    }

    /**
     * 根据正则表达式提取字符串 (只匹配一个)
     *
     * @param content 字符串
     * @param regex   正则表达式
     */
    public static String getFirstRegexString(String content, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    /**
     * 根据正则表达式提取字符串
     *
     * @param content 字符串
     * @param regex   正则表达式
     */
    public static List<String> getRegexString(String content, String regex) {
        List<String> result = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            result.add(matcher.group());
        }
        return result;
    }

    /**
     * Returns a substring before the last occurrence of [delimiter].
     * If the string does not contain the delimiter, returns [missingDelimiterValue] which defaults to the original string.
     */
    public static String substringBeforeLast(String source, String target) {
        int index = source.lastIndexOf(target);
        return index == -1 ? source : source.substring(0, index);
    }

    /**
     * Returns a substring after the last occurrence of [delimiter].
     * If the string does not contain the delimiter, returns [missingDelimiterValue] which defaults to the original string.
     */
    public static String substringAfterLast(String source, String target) {
        int index = source.lastIndexOf(target);
        return index == -1 ? source : source.substring(index + target.length());
    }
}
