package cn.techflowing.one.blog.user;

import cn.techflowing.one.blog.user.model.User;
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
}
