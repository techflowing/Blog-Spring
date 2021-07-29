package cn.techflowing.one.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * 类描述
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/7/28 1:01 上午
 */
@RunWith(SpringRunner.class)
public class JwtTest {

    @Test
    public void test_Jwt_generate() {
        Map<String, String> claim = new HashMap<>();
        claim.put("username", "techflowing");
        String result = JwtUtil.generateToken(claim, "123456", 1000 * 60 * 60);
        System.out.println(result);
    }

    @Test
    public void test_Jwt_getClaim() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Mjc0MTAzNTQsImlhdCI6MTYyNzQwNjc1NCwidXNlcm5hbWUiOiJ0ZWNoZmxvd2luZyJ9.yNy574-ipZ0v7eGthl6mMwm6Ps-0YrypUgTZJOXnUzs";
        Assert.assertEquals("techflowing", JwtUtil.getTokenClaim(token, "username"));
    }

    @Test
    public void test_Md5_hash() {
        String content = "admin";
        Assert.assertEquals("21232f297a57a5a743894a0e4a801fc3", Md5Util.getHashResult(content));
        System.out.println(Md5Util.getRepeatHashResult("21232f297a57a5a743894a0e4a801fc3", "1627492947000"));
    }
}
