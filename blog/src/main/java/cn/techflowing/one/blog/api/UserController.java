package cn.techflowing.one.blog.api;

import cn.techflowing.one.blog.user.UserService;
import cn.techflowing.one.blog.user.model.LoginRequestBody;
import cn.techflowing.one.blog.user.model.LoginResponse;
import cn.techflowing.one.blog.user.model.User;
import cn.techflowing.one.blog.user.util.PasswordHash;
import cn.techflowing.one.blog.user.util.TokenGenerator;
import cn.techflowing.one.util.StringUtil;
import cn.techflowing.one.common.response.Error;
import cn.techflowing.one.common.response.ErrorCode;
import cn.techflowing.one.common.response.Feature;
import cn.techflowing.one.common.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户相关Controller
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/7/29 12:15 上午
 */
@RestController
@RequestMapping("/blog/v1/user/")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("login")
    public Response<Object> login(@RequestBody LoginRequestBody body) {
        if (body == null) {
            return Response.emptyBody();
        }
        if (StringUtil.isEmpty(body.getUsername()) || StringUtil.isEmpty(body.getPassword())) {
            return Response.paramsError("用户名或者密码为空");
        }
        User user = userService.findUserByUsername(body.getUsername());
        if (user == null) {
            return Response.fail(Error.of(Feature.Blog.USER, ErrorCode.USER_NOT_EXIST, "用户不存在"));
        }
        String targetPwd = PasswordHash.hash(body.getPassword(), user.getCreateTime());
        if (!StringUtil.equals(targetPwd, user.getPassword())) {
            return Response.fail(Error.of(Feature.Blog.USER, ErrorCode.PWD_ERROR, "密码错误"));
        }
        // 登录成功，签发 Token
        LoginResponse response = new LoginResponse(user, TokenGenerator.generate(user));
        return Response.success(response);
    }

    @PostMapping("register")
    public Response<Object> register() {

        return Response.success();
    }
}
