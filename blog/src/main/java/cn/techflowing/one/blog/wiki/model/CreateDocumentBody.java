package cn.techflowing.one.blog.wiki.model;

import cn.techflowing.one.common.BaseModel;
import com.google.gson.annotations.SerializedName;

/**
 * 创建Doc请求体
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/8/25 12:53 上午
 */
public class CreateDocumentBody extends BaseModel {

    @SerializedName("name")
    private String name;
    @SerializedName("projectKey")
    private String projectKey;
    @SerializedName("isDir")
    private Boolean isDir;
    @SerializedName("parentId")
    private Integer parentId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProjectKey() {
        return projectKey;
    }

    public void setProjectKey(String projectKey) {
        this.projectKey = projectKey;
    }

    public Boolean getDir() {
        return isDir;
    }

    public void setDir(Boolean dir) {
        isDir = dir;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}