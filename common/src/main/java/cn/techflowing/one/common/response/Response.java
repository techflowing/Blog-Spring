package cn.techflowing.one.common.response;

/**
 * API 返回体定义
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/6/16 2:50 下午
 */
public class Response<T> {

    private int code = -1;
    private String message;
    private T data;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public Response<T> data(T data) {
        this.data = data;
        return this;
    }

    public Response<T> error(Error error) {
        this.code = error.getCode();
        this.message = error.getMessage();
        return this;
    }

    /**
     * 失败，不携带数据
     *
     * @param error 具体错误
     */
    public static Response<Object> fail(Error error) {
        return new Response<>().error(error).data("");
    }

    /**
     * 成功，没有任何数据要返回
     */
    public static Response<Object> success() {
        return new Response<>().error(Error.success()).data("");
    }

    /**
     * 请求体为空
     */
    public static Response<Object> emptyBody() {
        return new Response<>()
                .error(Error.of(Feature.COMMON, ErrorCode.PARAMS_ERROR, "请求体为空"))
                .data("");
    }

    /**
     * 请求体为空
     */
    public static Response<Object> paramsError(String message) {
        return new Response<>()
                .error(Error.of(Feature.COMMON, ErrorCode.PARAMS_ERROR, message))
                .data("");
    }

    /**
     * 成功，携带数据返回
     */
    public static <T> Response<T> success(T data) {
        return new Response<T>().error(Error.success()).data(data);
    }
}
