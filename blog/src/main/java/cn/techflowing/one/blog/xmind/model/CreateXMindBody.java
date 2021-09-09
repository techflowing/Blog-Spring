package cn.techflowing.one.blog.xmind.model;

import cn.techflowing.one.common.BaseModel;
import com.google.gson.annotations.SerializedName;

/**
 * 新建
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/9/5 3:42 下午
 */
public class CreateXMindBody extends BaseModel {

    @SerializedName("name")
    private String name;
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
