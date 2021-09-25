package cn.techflowing.one.blog.thought.model;

import cn.techflowing.one.common.BaseModel;

import java.util.Date;
import java.util.List;

/**
 * 随想录
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/9/22 1:00 上午
 */
public class Thought extends BaseModel {

    private String title;
    private String content;
    private String html;
    private List<String> tag;
    private String hashKey;
    private Date createTime;
    private Date updateTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }

    public String getHashKey() {
        return hashKey;
    }

    public void setHashKey(String hashKey) {
        this.hashKey = hashKey;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
