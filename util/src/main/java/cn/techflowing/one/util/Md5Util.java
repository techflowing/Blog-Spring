package cn.techflowing.one.util;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * MD5 工具类
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/7/29 1:26 上午
 */
public class Md5Util {

    /** hash 次数 */
    public static final int HASH_ITERATIONS = 1024;
    /** 算法 */
    public static final String ALGORITHM_NAME = "MD5";

    /**
     * 获取重复hash 结果，hash 1024 次
     *
     * @param content 内筒
     * @param salt    盐
     */
    public static String getRepeatHashResult(String content, String salt) {
        return getRepeatHashResult(content, salt, HASH_ITERATIONS);
    }

    /**
     * 获取重复hash 结果
     *
     * @param content 内筒
     * @param salt    盐
     */
    public static String getRepeatHashResult(String content, String salt, int hashTime) {
        return new SimpleHash(ALGORITHM_NAME, content, salt, hashTime).toString();
    }

    /**
     * 获取重复hash 结果，hash 1024 次
     *
     * @param content 内筒
     */
    public static String getHashResult(String content) {
        return new SimpleHash(ALGORITHM_NAME, content).toString();
    }
}
