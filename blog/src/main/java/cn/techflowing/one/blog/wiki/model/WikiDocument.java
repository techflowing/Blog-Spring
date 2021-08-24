package cn.techflowing.one.blog.wiki.model;

import cn.techflowing.one.common.BaseModel;
import com.google.gson.annotations.SerializedName;

import java.util.Comparator;
import java.util.List;

/**
 * Wiki Document 查询行
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/8/11 1:00 上午
 */
public class WikiDocument extends BaseModel {

    public static final int TYPE_DOC = 0;
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
    private List<WikiDocument> children;
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
        this.isLeaf = (type == TYPE_DOC);
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

    public List<WikiDocument> getChildren() {
        return children;
    }

    public void setChildren(List<WikiDocument> children) {
        this.children = children;
        if (this.children != null) {
            this.children.sort(Comparator.comparingInt(o -> o.sort));
        }
    }
}
