package cn.techflowing.one.blog.statistic.model;

/**
 * 访问量统计
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/9/15 12:49 上午
 */
public class VisitorStatistic {

    private String scene;
    private String location;
    private int uv;
    private int pv;

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

    public int getUv() {
        return uv;
    }

    public void setUv(int uv) {
        this.uv = uv;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }
}
