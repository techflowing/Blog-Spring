package cn.techflowing.one.blog.user.model;

import cn.techflowing.one.common.BaseModel;

import java.util.Date;

/**
 * User 信息
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/7/29 11:43 下午
 */
public class User extends BaseModel {

    private String username;
    private transient String password;
    private String mobile;
    private String email;
    private String avatar;
    private boolean isAdmin;
    private transient int roleLevel;
    private String roleDesc;
    private transient Date createTime;

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(int roleLevel) {
        this.roleLevel = roleLevel;
        this.isAdmin = Role.isAdmin(roleLevel);
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
