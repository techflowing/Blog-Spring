package cn.techflowing.one.blog.user.util;

import cn.techflowing.one.util.Md5Util;

import java.util.Date;

/**
 * 密码Hash
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/7/30 12:57 上午
 */
public class PasswordHash {

    public static String hash(String password, Date creatTime) {
        return Md5Util.getRepeatHashResult(password, String.valueOf(creatTime.getTime()));
    }
}
