package cn.techflowing.one.blog.xmind.model;

import cn.techflowing.one.common.BaseModel;
import com.google.gson.annotations.SerializedName;

/**
 * 重命名请求
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/8/25 1:14 上午
 */
public class RenameXMindtBody extends BaseModel {

    @SerializedName("name")
    private String name;
    @SerializedName("id")
    private Integer id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
