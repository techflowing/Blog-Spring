package cn.techflowing.one.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 配置项Service
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/8/1 10:11 下午
 */
@Service
public class ConfigService {

    @Autowired
    ConfigMapper configMapper;

    public Config queryConfig(String name) {
        return configMapper.getConfig(name);
    }

    public boolean updateOrCreateConfig(String name, Object content, String desc) {
        return configMapper.updateConfig(name, content, desc) > 0;
    }
}
