package cn.techflowing.one.blog.wiki.model;

import cn.techflowing.one.common.BaseModel;

/**
 * Wiki 项目
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/8/8 10:10 下午
 */
public class WikiProject extends BaseModel {
    private String name;
    private String description;
    private int docCount;
    private String thumb;
    private String hashKey;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDocCount() {
        return docCount;
    }

    public void setDocCount(int docCount) {
        this.docCount = docCount;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getHashKey() {
        return hashKey;
    }

    public void setHashKey(String hashKey) {
        this.hashKey = hashKey;
    }
}
