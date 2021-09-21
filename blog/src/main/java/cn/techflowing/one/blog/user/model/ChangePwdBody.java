package cn.techflowing.one.blog.user.model;

/**
 * 修改密码
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/9/21 2:41 下午
 */
public class ChangePwdBody {

    private String oldPassword;
    private String newPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
