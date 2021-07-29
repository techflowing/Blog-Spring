package cn.techflowing.one.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * JSON Web Token
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/7/28 12:44 上午
 */
public final class JwtUtil {

    /**
     * 签发Token
     *
     * @param claim      数据
     * @param secret     秘钥
     * @param expireTime 过期时间，毫秒单位
     */
    public static String generateToken(Map<String, String> claim, String secret, long expireTime) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        long curTime = System.currentTimeMillis();
        JWTCreator.Builder builder = JWT.create();
        claim.forEach(builder::withClaim);
        return builder.withExpiresAt(new Date(curTime + expireTime))
                .withIssuedAt(new Date(curTime))
                .sign(algorithm);
    }

    /**
     * 验证Token
     *
     * @param token  原始Token
     * @param claim  数据
     * @param secret 秘钥
     */
    public static boolean verify(String token, Map<String, String> claim, String secret) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        Verification verification = JWT.require(algorithm);
        claim.forEach(verification::withClaim);
        try {
            verification.build().verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    /**
     * 判断Token 是否过期
     *
     * @param token token
     */
    public static boolean isTokenExpired(String token) {
        Date now = Calendar.getInstance().getTime();
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getExpiresAt().before(now);
    }

    /**
     * 获取Token 中的签发数据
     *
     * @param token token
     * @param key   key
     * @return
     */
    public static String getTokenClaim(String token, String key) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(key).asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
}
