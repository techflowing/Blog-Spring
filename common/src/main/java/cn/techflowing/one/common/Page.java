package cn.techflowing.one.common;

import java.util.List;

/**
 * 分页数据模型
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/6/29 2:13 下午
 */
public class Page<T> {

    /** 数据列表 */
    private List<T> content;
    /** 页码 */
    private int page;
    /** 每页大小 */
    private int size;
    /** 数据总长度 */
    private int total;

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
