package cn.techflowing.one.common.response;

/**
 * 错误码定义，最大值 9999
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/7/29 12:19 上午
 */
public interface ErrorCode {

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
    /** 结果为空 */
    int RESULT_EMPTY = 7;
    /** Token 过期 */
    int TOKEN_EXPIRED = 8;
    /** Token 验证失败 */
    int TOKEN_VERIFY_FAIL = 9;

    /** 用户不存在 */
    int USER_NOT_EXIST = 101;
    int PWD_ERROR = 102;
}
