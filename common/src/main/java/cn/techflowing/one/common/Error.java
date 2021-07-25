package cn.techflowing.one.common;

/**
 * API错误定义
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/6/16 2:45 下午
 */
public class Error {

    private int feature;
    private int error;
    private String message;

    public Error feature(int feature) {
        this.feature = feature;
        return this;
    }

    public Error error(int error) {
        this.error = error;
        return this;
    }

    public Error message(String message) {
        this.message = message;
        return this;
    }

    public int getCode() {
        return feature * 10000 + error;
    }

    public String getMessage() {
        return message;
    }

    public static Error of(int feature, int error, String message) {
        return new Error()
                .feature(feature)
                .error(error)
                .message(message);
    }

    public static Error success() {
        return new Error()
                .feature(0)
                .error(ErrDef.Error.SUCCESS)
                .message("SUCCESS");
    }
}
