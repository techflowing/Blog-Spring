package cn.techflowing.one.blog.user.util;

import cn.techflowing.one.blog.user.model.User;
import cn.techflowing.one.util.JwtUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Token 生成器
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/7/30 1:06 上午
 */
public class TokenGenerator {

    /** 过期时间,7 天 */
    private static final int EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 7;

    public static String generate(User user) {
        Map<String, String> claim = new HashMap<>();
        claim.put("username", user.getUsername());
        claim.put("roleLevel", String.valueOf(user.getRoleLevel()));
        claim.put("roleDesc", user.getRoleDesc());
        return JwtUtil.generateToken(claim, String.valueOf(user.getCreateTime().getTime()), EXPIRATION_TIME);
    }
}
