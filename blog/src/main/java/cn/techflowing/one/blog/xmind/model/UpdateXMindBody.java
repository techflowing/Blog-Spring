package cn.techflowing.one.blog.xmind.model;

import cn.techflowing.one.common.BaseModel;

/**
 * 更新文档内容
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/9/3 10:56 下午
 */
public class UpdateXMindBody extends BaseModel {

    private String content;
    private String hashKey;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHashKey() {
        return hashKey;
    }

    public void setHashKey(String hashKey) {
        this.hashKey = hashKey;
    }
}
