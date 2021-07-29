package cn.techflowing.one.common.response;

/**
 * 业务类型
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/7/29 12:19 上午
 */
public interface Feature {

    /** 公共业务 */
    int COMMON = 1000;

    /**
     * 博客业务
     * 占用 1100 - 1199
     */
    interface Blog {
        /** 用户相关 */
        int USER = 1100;
    }
}
