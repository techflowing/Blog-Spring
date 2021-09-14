package cn.techflowing.one.blog.statistic.model;

import cn.techflowing.one.common.BaseModel;

/**
 * 统计事件
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/9/14 12:02 上午
 */
public class StatisticEvent<T> extends BaseModel {

    private String date;
    private String name;
    private String scene;
    private String location;
    private String ip;
    private T content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
