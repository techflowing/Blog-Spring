package cn.techflowing.one.blog.xmind.model;

import cn.techflowing.one.common.BaseModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 删除请求
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/8/27 1:27 上午
 */
public class DeleteXMindBody extends BaseModel {

    @SerializedName("id")
    private List<Integer> id;

    public List<Integer> getId() {
        return id;
    }

    public void setId(List<Integer> id) {
        this.id = id;
    }
}
