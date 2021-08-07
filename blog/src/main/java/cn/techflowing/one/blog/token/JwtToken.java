package cn.techflowing.one.blog.token;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author techflowing
 * @since 2019-04-13 23:28
 */
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String token) {
        this.token = token;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
