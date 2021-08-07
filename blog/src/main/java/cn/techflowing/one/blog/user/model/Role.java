package cn.techflowing.one.blog.user.model;

/**
 * 权限
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/8/4 11:07 下午
 */
public enum Role {
    NORMAL(0),
    ADMIN(100),
    SUPER_ADMIN(999);

    private int roleLevel;

    Role(int roleLevel) {
        this.roleLevel = roleLevel;
    }

    public static boolean isAdmin(int roleLevel) {
        return roleLevel >= ADMIN.roleLevel;
    }
}
