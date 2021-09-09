package cn.techflowing.one.blog.xmind.model;

import cn.techflowing.one.common.BaseModel;
import com.google.gson.annotations.SerializedName;

import java.util.Comparator;
import java.util.List;

/**
 * 思维导图
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/9/4 3:47 下午
 */
public class XMind extends BaseModel {

    public static final int TYPE_MAP = 0;
    public static final int TYPE_DIR = 1;

    private int id;
    private transient int type;
    private int parentId;
    private transient int sort;
    @SerializedName("title")
    private String name;
    @SerializedName("key")
    private String hashKey;
    @SerializedName("children")
    private List<XMind> children;
    @SerializedName("isLeaf")
    private boolean isLeaf;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
        this.isLeaf = (type == TYPE_MAP);
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHashKey() {
        return hashKey;
    }

    public void setHashKey(String hashKey) {
        this.hashKey = hashKey;
    }

    public List<XMind> getChildren() {
        return children;
    }

    public void setChildren(List<XMind> children) {
        this.children = children;
        if (this.children != null) {
            this.children.sort(Comparator.comparingInt(o -> o.sort));
        }
    }

    public boolean isLeaf() {
        return isLeaf;
    }
}
