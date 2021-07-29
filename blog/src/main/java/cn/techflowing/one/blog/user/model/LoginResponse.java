package cn.techflowing.one.blog.user.model;

import cn.techflowing.one.common.BaseModel;

/**
 * 登录结果返回
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/7/30 1:01 上午
 */
public class LoginResponse extends BaseModel {

    private User user;
    private String token;

    public LoginResponse(User user, String token) {
        this.user = user;
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
