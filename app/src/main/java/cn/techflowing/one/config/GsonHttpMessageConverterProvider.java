package cn.techflowing.one.config;

import cn.techfllowing.one.util.GsonUtil;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * 构建 GsonHttpMessageConverter
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/7/14 2:03 下午
 */
public class GsonHttpMessageConverterProvider {

    public static GsonHttpMessageConverter create() {
        GsonHttpMessageConverter gsonHttpMessageConverter = new GsonHttpMessageConverter();
        List<MediaType> mediaTypeList = new ArrayList<>();
        mediaTypeList.add(MediaType.APPLICATION_JSON);
        mediaTypeList.add(MediaType.APPLICATION_JSON_UTF8);
        mediaTypeList.add(new MediaType("text", "json", StandardCharsets.UTF_8));
        gsonHttpMessageConverter.setSupportedMediaTypes(mediaTypeList);
        gsonHttpMessageConverter.setGson(GsonUtil.getDefaultGson());
        return gsonHttpMessageConverter;
    }
}
