package cn.techflowing.one.blog.wiki.model;

import cn.techflowing.one.common.BaseModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 删除文档请求
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/8/27 1:27 上午
 */
public class DeleteDocumentBody extends BaseModel {

    @SerializedName("projectKey")
    private String projectKey;
    @SerializedName("documentId")
    private List<Integer> documentId;

    public String getProjectKey() {
        return projectKey;
    }

    public void setProjectKey(String projectKey) {
        this.projectKey = projectKey;
    }

    public List<Integer> getDocumentId() {
        return documentId;
    }

    public void setDocumentId(List<Integer> documentId) {
        this.documentId = documentId;
    }
}
