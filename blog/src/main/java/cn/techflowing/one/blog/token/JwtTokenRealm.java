package cn.techflowing.one.blog.token;

import cn.techflowing.one.blog.user.UserMapper;
import cn.techflowing.one.blog.user.model.User;
import cn.techflowing.one.blog.user.util.TokenGenerator;
import cn.techflowing.one.util.JwtUtil;
import cn.techflowing.one.util.StringUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * JWT Token 验证
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/8/7 7:48 下午
 */
public class JwtTokenRealm extends AuthorizingRealm {

    @Autowired
    UserMapper userMapper;

    public JwtTokenRealm() {
        setCredentialsMatcher((authenticationToken, authenticationInfo) -> {
            User user = (User) authenticationInfo.getPrincipals().getPrimaryPrincipal();
            String token = (String) authenticationInfo.getCredentials();
            return TokenGenerator.verify(token, user);
        });
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) authenticationToken;
        String token = jwtToken.getToken();
        String username = TokenGenerator.getUserNameFromToken(token);
        if (StringUtil.isEmpty(username)) {
            throw new UnknownAccountException();
        }
        User user = userMapper.findUserByUsername(username);
        if (user == null) {
            throw new UnknownAccountException();
        }
        if (JwtUtil.isTokenExpired(token)) {
            throw new ExpiredCredentialsException();
        }
        if (!TokenGenerator.verify(token, user)) {
            throw new IncorrectCredentialsException();
        }
        return new SimpleAuthenticationInfo(user, token, getName());
    }
}
