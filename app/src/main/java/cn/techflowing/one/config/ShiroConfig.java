package cn.techflowing.one.config;

import cn.techflowing.one.blog.token.JwtAuthFilter;
import cn.techflowing.one.blog.token.JwtTokenRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.*;

/**
 * Shiro 拦截配置
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/8/7 7:32 下午
 */
@Configuration
public class ShiroConfig {

    /**
     * Filter工厂，设置对应的过滤条件和跳转条件
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 自定义拦截器
        Map<String, Filter> filterMap = shiroFilterFactoryBean.getFilters();
        filterMap.put("blogAuthToken", new JwtAuthFilter());
        shiroFilterFactoryBean.setFilters(filterMap);

        // 针对不同请求设置拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 博客站 Admin 请求拦截
        filterChainDefinitionMap.put("/blog/v1/admin/**", "blogAuthToken");
        // 其余请求不验证
        filterChainDefinitionMap.put("/**", "anon");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealms(getRealms());
        return securityManager;
    }

    private List<Realm> getRealms() {
        List<Realm> list = new ArrayList<>();
        list.add(jwtTokenRealm());
        return list;
    }

    /**
     * 必须要这样写 Bean
     */
    @Bean
    public JwtTokenRealm jwtTokenRealm() {
        return new JwtTokenRealm();
    }
}
