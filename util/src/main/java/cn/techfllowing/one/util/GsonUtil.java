package cn.techfllowing.one.util;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.Map;

/**
 * Gson处理
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/4/9 3:54 下午
 */
public class GsonUtil {

    private static final Gson gson;
    private static final Gson prettyGson;

    static {
        DateTimestampTypeAdapter adapter = new DateTimestampTypeAdapter();
        gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, adapter)
                .create();
        prettyGson = new GsonBuilder()
                .registerTypeAdapter(Date.class, adapter)
                .setPrettyPrinting()
                .create();
    }

    /**
     * 私有方法
     */
    private GsonUtil() {

    }

    /**
     * 获取默认的Gson
     */
    public static Gson getDefaultGson() {
        return gson;
    }

    /**
     * 将object对象转成json字符串
     *
     * @param object model 类
     */
    public static String toString(Object object) {
        String result = null;
        if (gson != null) {
            result = gson.toJson(object);
        }
        return result;
    }

    /**
     * 将object对象转成json字符串，带有格式化
     *
     * @param object model 类
     */
    public static String toPrettyString(Object object) {
        String result = null;
        if (prettyGson != null) {
            result = prettyGson.toJson(object);
        }
        return result;
    }


    /**
     * 将gsonString转成泛型bean
     *
     * @param content string 内容
     * @param cls     类
     */
    public static <T> T convertJson(String content, Class<T> cls) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(content, cls);
        }
        return t;
    }

    /**
     * 将gsonString转成泛型bean
     *
     * @param content string 内容
     * @param type    类型
     */
    public static <T> T convertJson(String content, Type type) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(content, type);
        }
        return t;
    }

    /**
     * 转成map的
     *
     * @param content string 内容
     * @return Map
     */
    public static <T> Map<String, T> convertJsonToMap(String content) {
        Map<String, T> map = null;
        if (gson != null) {
            map = gson.fromJson(content, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }

    /**
     * 转换为JsonObject
     *
     * @param content 文本内容
     */
    public static JsonObject convertJsonObject(String content) {
        return JsonParser.parseString(content).getAsJsonObject();
    }

    /**
     * 转换为JsonArray
     *
     * @param content 文本内容
     */
    public static JsonArray convertJsonArray(String content) {
        return JsonParser.parseString(content).getAsJsonArray();
    }

    /**
     * Date 类型和 毫秒时间戳互相转换
     */
    private static class DateTimestampTypeAdapter extends TypeAdapter<Date> {

        @Override
        public void write(JsonWriter out, Date value) throws IOException {
            if (value == null) {
                out.nullValue();
                return;
            }
            out.value(value.getTime());
        }

        @Override
        public Date read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            return new Date(Long.parseLong(in.nextString()));
        }
    }
}
