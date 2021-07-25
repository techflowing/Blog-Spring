package cn.techflowing.one.config;

import cn.techfllowing.one.util.LogUtil;
import cn.techflowing.one.common.Environment;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Configuration
public class RestTemplateConfig {

    @Autowired
    RestTemplateBuilder builder;

    @Value("${spring.profiles.active}")
    private String env;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = builder.build();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                LogUtil.log("HttpLog: " + message);
            }
        });
        if (Environment.isProdEnv()) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        } else {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectionPool(new ConnectionPool(8, 10, TimeUnit.SECONDS))
                .readTimeout(600, TimeUnit.SECONDS)
                .connectTimeout(600, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build();
        restTemplate.setRequestFactory(new OkHttp3ClientHttpRequestFactory(okHttpClient));

        restTemplate.getMessageConverters().removeIf(item -> {
            return item.getClass() == MappingJackson2HttpMessageConverter.class;
        });

        restTemplate.getMessageConverters().add(GsonHttpMessageConverterProvider.create());
        restTemplate.getMessageConverters().add(new FormHttpMessageConverter());

        restTemplate.getMessageConverters().forEach(item -> {
            LogUtil.log("解析器：" + item.getClass().getSimpleName());
        });

        LogUtil.log("创建了一个 RestTemplate：" + restTemplate.hashCode());
        return restTemplate;
    }
}
