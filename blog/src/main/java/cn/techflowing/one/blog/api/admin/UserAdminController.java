package cn.techflowing.one.blog.api.admin;

import cn.techflowing.one.blog.user.UserService;
import cn.techflowing.one.blog.user.model.ChangePwdBody;
import cn.techflowing.one.common.response.Error;
import cn.techflowing.one.common.response.Response;
import cn.techflowing.one.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 账户管理
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/9/21 2:37 下午
 */
@RestController
@RequestMapping("/blog/v1/admin/user/")
public class UserAdminController {

    @Autowired
    UserService userService;

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
