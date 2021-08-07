package cn.techflowing.one.blog.token;

import cn.techflowing.one.common.response.Error;
import cn.techflowing.one.common.response.ErrorCode;
import cn.techflowing.one.common.response.Feature;
import cn.techflowing.one.common.response.Response;
import cn.techflowing.one.util.GsonUtil;
import cn.techflowing.one.util.StringUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义 Filter 拦截请求
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/8/7 8:00 下午
 */
public class JwtAuthFilter extends BasicHttpAuthenticationFilter {

    /**
     * 检验请求头是否带有 token
     * 如果带有 token，执行 shiro 的 login() 方法，将 token 提交到 Realm 中进行检验；
     * 如果没有 token，onAccessDenied
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        String token = WebUtils.toHttp(request).getHeader("token");
        if (StringUtil.isEmpty(token)) {
            responseAuthFail(response, ErrorCode.TOKEN_EMPTY, "Token 为空");
            return false;
        }
        JwtToken jwtToken = new JwtToken(token);
        try {
            getSubject(request, response).login(jwtToken);
            return true;
        } catch (ExpiredCredentialsException e) {
            responseAuthFail(response, ErrorCode.TOKEN_EXPIRED, "Token 过期");
        } catch (IncorrectCredentialsException e) {
            responseAuthFail(response, ErrorCode.TOKEN_VERIFY_FAIL, "Token 验证失败");
        } catch (UnknownAccountException e) {
            responseAuthFail(response, ErrorCode.TOKEN_VERIFY_FAIL, "Token 信息异常");
        } catch (AuthenticationException e) {
            responseAuthFail(response, ErrorCode.TOKEN_VERIFY_FAIL, "Token 验证失败");
        }
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        return false;
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        // 添加跨域支持，必须要在 preHandle 之前
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        return super.preHandle(request, response);
    }

    /**
     * 返回异常
     *
     * @param servletResponse 返回数据
     */
    private void responseAuthFail(ServletResponse servletResponse, int errCode, String errMsg) {
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        httpResponse.setContentType("application/json;charset=utf-8");
        Response<Object> response = Response.fail(Error.of(Feature.Blog.TOKEN, errCode, errMsg));
        try {
            httpResponse.getWriter().println(GsonUtil.toString(response));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}