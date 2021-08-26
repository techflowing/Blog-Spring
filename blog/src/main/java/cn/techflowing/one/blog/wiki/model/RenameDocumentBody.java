package cn.techflowing.one.blog.wiki.model;

import com.google.gson.annotations.SerializedName;

/**
 * 重命名请求
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/8/25 1:14 上午
 */
public class RenameDocumentBody {

    @SerializedName("name")
    private String name;
    @SerializedName("documentId")
    private Integer documentId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }
}
