package cn.techflowing.one.common;

/**
 * 错误定义  XXX-XXXX 格式
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/6/16 2:46 下午
 */
public class ErrDef {

    public interface Feature {

    }

    public interface Error {
        /** 成功 */
        int SUCCESS = 0;
        /** 数据库查询错误 */
        int DB_ERROR = 1;
        /** 参数错误 */
        int PARAMS_ERROR = 2;
        /** 请求三方服务异常 */
        int THIRD_SERVER_ERROR = 3;
        /** 文件为空 */
        int FILE_EMPTY = 4;
        /** 文件不存在 */
        int FILE_NOT_EXIST = 5;
        /** IO异常 */
        int IO_ERROR = 6;
    }
}
