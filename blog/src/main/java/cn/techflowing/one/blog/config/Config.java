package cn.techflowing.one.blog.config;

import cn.techflowing.one.common.BaseModel;

/**
 * 配置数据
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/8/1 10:38 下午
 */
public class Config extends BaseModel {

    private String name;
    private Object content;
    private String desc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
