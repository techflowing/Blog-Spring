package cn.techflowing.one.blog.user;

import cn.techflowing.one.blog.user.model.ChangePwdBody;
import cn.techflowing.one.blog.user.model.User;
import cn.techflowing.one.blog.user.util.PasswordHash;
import cn.techflowing.one.blog.user.util.TokenGenerator;
import cn.techflowing.one.common.response.Error;
import cn.techflowing.one.common.response.ErrorCode;
import cn.techflowing.one.common.response.Feature;
import cn.techflowing.one.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 账户相关Service
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/7/29 12:28 上午
 */
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public User findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }

    /**
     * 修改密码
     *
     * @param token token 信息
     * @param body  修改密码数据
     */
    public Error changePassword(String token, ChangePwdBody body) {
        String username = TokenGenerator.getUserNameFromToken(token);
        if (StringUtil.isEmpty(username)) {
            return Error.of(Feature.Blog.TOKEN, ErrorCode.TOKEN_VERIFY_FAIL, "Token 异常");
        }
        User user = findUserByUsername(username);
        if (user == null) {
            return Error.of(Feature.Blog.USER, ErrorCode.USER_NOT_EXIST, "用户不存在");
        }
        String targetPwd = PasswordHash.hash(body.getOldPassword(), user.getCreateTime());
        if (!StringUtil.equals(targetPwd, user.getPassword())) {
            return Error.of(Feature.Blog.USER, ErrorCode.PWD_ERROR, "旧密码错误");
        }
        String newPwd = PasswordHash.hash(body.getNewPassword(), user.getCreateTime());
        return userMapper.changePassword(username, newPwd) > 0 ?
                null : Error.of(Feature.COMMON, ErrorCode.DB_ERROR, "数据库异常");
    }
}
