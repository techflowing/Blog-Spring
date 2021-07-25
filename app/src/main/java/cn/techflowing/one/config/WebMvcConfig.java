package cn.techflowing.one.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * 跨域配置
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/6/16 9:04 下午
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        // 设置允许跨域的路由
        registry.addMapping("/**")
                // 设置允许跨域请求的域名
                .allowedOriginPatterns("*")
                // 是否允许证书（cookies）
                .allowCredentials(true)
                // 设置允许的方法
                .allowedMethods("*")
                .allowedHeaders("*")
                // 跨域允许时间
                .maxAge(3600);
        super.addCorsMappings(registry);
    }

    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.removeIf(item -> {
            return item.getClass() == MappingJackson2HttpMessageConverter.class;
        });
        converters.add(GsonHttpMessageConverterProvider.create());
    }
}
