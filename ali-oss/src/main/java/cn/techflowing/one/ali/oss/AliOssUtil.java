package cn.techflowing.one.ali.oss;

import cn.techflowing.one.util.DateUtil;
import cn.techflowing.one.util.Md5Util;

/**
 * 工具类
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/12/28 1:28 上午
 */
public class AliOssUtil {

    /**
     * 获取上传后的文件名称
     */
    public static String formatFileName(String originFileName) {
        if (originFileName == null || originFileName.lastIndexOf(".") < 0) {
            return String.valueOf(System.currentTimeMillis());
        }
        String postfix = originFileName.substring(originFileName.lastIndexOf("."));
        String md5 = Md5Util.getMd5(originFileName);
        return String.format("%s%s%s", System.currentTimeMillis(), md5, postfix);
    }

    /**
     * 获取图片上传路径
     */
    public static String getImageUploadKey(String fileName) {
        String date = DateUtil.formatTimeToMonthDay(System.currentTimeMillis());
        return String.format("media-store/image/%s/%s", date, fileName);
    }

    /**
     * 获取文件上传路径
     */
    public static String getFileUploadKey(String fileName) {
        String date = DateUtil.formatTimeToMonthDay(System.currentTimeMillis());
        return String.format("media-store/file/%s/%s", date, fileName);
    }
}
