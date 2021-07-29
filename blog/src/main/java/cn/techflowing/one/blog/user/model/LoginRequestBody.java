package cn.techflowing.one.blog.user.model;

import cn.techflowing.one.common.BaseModel;

/**
 * 登录请求
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/7/29 12:29 上午
 */
public class LoginRequestBody extends BaseModel {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
