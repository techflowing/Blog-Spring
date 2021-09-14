package cn.techflowing.one.blog.statistic.model;

import cn.techflowing.one.common.BaseModel;
import com.google.gson.annotations.SerializedName;

/**
 * 访问事件
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/9/14 12:04 上午
 */
public class VisitorContent extends BaseModel {

    @SerializedName("device")
    private String device;
    @SerializedName("platform")
    private String platform;
    @SerializedName("browser")
    private String browser;

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }
}
