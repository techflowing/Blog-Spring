package cn.techflowing.one.blog.user;

import cn.techflowing.one.blog.user.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 账户相关Mapper
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/7/29 12:28 上午
 */
@Mapper
public interface UserMapper {

    String USER_INFO_TABLE = "blog_user_info";
    String USER_ROLE_TABLE = "blog_user_role";

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     */
    @Select("select username,\n" +
            "       password,\n" +
            "       mobile,\n" +
            "       email,\n" +
            "       avatar,\n" +
            "       create_time,\n" +
            "       role.level as role_level,\n" +
            "       role.desc  as role_desc\n" +
            "from (select * from " + USER_INFO_TABLE + " where username = #{username} limit 1) user\n" +
            "         join " + USER_ROLE_TABLE + " role\n" +
            "where user.role = role.id")
    User findUserByUsername(String username);
}
