package cn.techflowing.one.blog.api.admin;

import cn.techflowing.one.ali.oss.AliOssClient;
import cn.techflowing.one.common.response.Error;
import cn.techflowing.one.common.response.ErrorCode;
import cn.techflowing.one.common.response.Feature;
import cn.techflowing.one.common.response.Response;
import cn.techflowing.one.util.DateUtil;
import cn.techflowing.one.util.Md5Util;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件上传接口，上传到阿里云 OSS
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/8/20 11:34 下午
 */
@RestController
@RequestMapping("/blog/v1/admin/upload/")
public class FileUploadController {

    private static final String BUCKET_NAME = "blog-techflowing";

    @PostMapping("image")
    public Object uploadImage(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return Response.paramsError("数据为空");
        }
        try {
            String fileName = formatFileName(file.getOriginalFilename());
            String key = getImageUploadKey(fileName);
            boolean result = AliOssClient.get().uploadFile(BUCKET_NAME, key, file.getInputStream());
            return result ? Response.success(AliOssClient.getUrl(key)) :
                    Response.fail(Error.of(Feature.Blog.OSS, ErrorCode.FILE_UPLOAD_FAIL, "上传失败"));
        } catch (IOException e) {
            return Response.error(e.getMessage());
        }
    }

    /**
     * 构建上传结果，适配 Ant-Design Upload 组件
     */
    private Map<String, String> buildImageUploadResult(String name, String url) {
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("status", "done");
        map.put("url", url);
        map.put("thumbUrl", url);
        return map;
    }

    /**
     * 获取图片上传路径
     */
    private String getImageUploadKey(String fileName) {
        String date = DateUtil.formatTimeToMonthDay(System.currentTimeMillis());
        return String.format("media-store/image/%s/%s", date, fileName);
    }

    /**
     * 获取上传后的文件名称
     */
    private String formatFileName(String originFileName) {
        if (originFileName == null || originFileName.lastIndexOf(".") < 0) {
            return String.valueOf(System.currentTimeMillis());
        }
        String postfix = originFileName.substring(originFileName.lastIndexOf("."));
        String md5 = Md5Util.getMd5(originFileName);
        return String.format("%s%s%s", System.currentTimeMillis(), md5, postfix);
    }
}
