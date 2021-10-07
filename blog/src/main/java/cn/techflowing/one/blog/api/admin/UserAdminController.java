package cn.techflowing.one.blog.api.admin;

import cn.techflowing.one.blog.user.UserService;
import cn.techflowing.one.blog.user.model.ChangePwdBody;
import cn.techflowing.one.common.response.Error;
import cn.techflowing.one.common.response.Response;
import cn.techflowing.one.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.techflowing.one.blog.user.model.User;
import cn.techflowing.one.blog.user.util.TokenGenerator;
import cn.techflowing.one.common.response.ErrorCode;
import cn.techflowing.one.common.response.Feature;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户管理
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/10/8 12:22 上午
 */
@RestController
@RequestMapping("/blog/v1/admin/user/")
public class UserAdminController {

    @Autowired
    UserService userService;

    @GetMapping("refresh-token")
    public Response<Object> refreshToken(@RequestHeader(name = "token") String token) {
        String username = TokenGenerator.getUserNameFromToken(token);
        User user = userService.findUserByUsername(username);
        if (user == null) {
            return Response.fail(Error.of(Feature.Blog.USER, ErrorCode.USER_NOT_EXIST, "用户不存在"));
        }
        return Response.success(TokenGenerator.generate(user));
    }

    @PostMapping("change-pwd")
    public Response<Object> changePwd(@RequestHeader(name = "token") String token,
                                      @RequestBody ChangePwdBody body) {
        if (body == null) {
            return Response.emptyBody();
        }
        if (StringUtil.isEmpty(token)) {
            return Response.paramsError("token 缺失");
        }
        Error result = userService.changePassword(token, body);
        return result == null ? Response.success() : Response.fail(result);
    }
}
