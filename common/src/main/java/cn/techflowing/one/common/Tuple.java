package cn.techflowing.one.common;

/**
 * 数据处理元组
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/7/1 9:02 下午
 */
public class Tuple<T> {

    /** 错误码 */
    private int errCode;
    /** 结果 */
    private T result;

    private Tuple(int errCode) {
        this.errCode = errCode;
    }

    private Tuple(int errCode, T result) {
        this.errCode = errCode;
        this.result = result;
    }

    public int getErrCode() {
        return errCode;
    }

    public T getResult() {
        return result;
    }

    public static <T> Tuple<T> create(int errCode) {
        return new Tuple<T>(errCode);
    }

    public static <T> Tuple<T> create(int errCode, T result) {
        return new Tuple<T>(errCode, result);
    }
}
