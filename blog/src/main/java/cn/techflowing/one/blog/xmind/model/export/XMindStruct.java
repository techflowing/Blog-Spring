package cn.techflowing.one.blog.xmind.model.export;

import com.google.gson.annotations.SerializedName;

/**
 * Xmind 结构数据
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/9/8 11:46 下午
 */
public class XMindStruct {

    @SerializedName("root")
    private Node root;
    @SerializedName("template")
    private String template;
    @SerializedName("theme")
    private String theme;
    @SerializedName("version")
    private String version;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
