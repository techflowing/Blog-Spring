package cn.techflowing.one.blog.xmind.model.export;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Xmind 节点Node
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/9/8 11:41 下午
 */
public class Node {

    @SerializedName("data")
    private Data data;
    @SerializedName("children")
    private List<Node> children;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public static class Data {
        @SerializedName("id")
        private String id;
        @SerializedName("text")
        private String text;
        @SerializedName("created")
        private Long created;
        @SerializedName("hyperlink")
        private String hyperlink;
        @SerializedName("hyperlinkTitle")
        private String hyperlinkTitle;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Long getCreated() {
            return created;
        }

        public void setCreated(Long created) {
            this.created = created;
        }

        public String getHyperlink() {
            return hyperlink;
        }

        public void setHyperlink(String hyperlink) {
            this.hyperlink = hyperlink;
        }

        public String getHyperlinkTitle() {
            return hyperlinkTitle;
        }

        public void setHyperlinkTitle(String hyperlinkTitle) {
            this.hyperlinkTitle = hyperlinkTitle;
        }
    }
}
