package cn.techflowing.one.common;

import cn.techflowing.one.util.StringUtil;
import org.springframework.beans.factory.annotation.Value;

/**
 * 运行环境判定
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/7/7 4:42 下午
 */
public class Environment {

    private static final Environment INSTANCE = new Environment();

    @Value("${spring.profiles.active}")
    private String env;

    private Environment() {
    }

    private boolean isProd() {
        return StringUtil.equals(env, "prod");
    }

    public static boolean isProdEnv() {
        return INSTANCE.isProd();
    }
}
