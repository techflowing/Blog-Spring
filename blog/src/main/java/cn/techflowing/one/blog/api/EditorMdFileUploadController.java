package cn.techflowing.one.blog.api;

import cn.techflowing.one.ali.oss.AliOssClient;
import cn.techflowing.one.ali.oss.AliOssConfig;
import cn.techflowing.one.ali.oss.AliOssUtil;
import cn.techflowing.one.common.response.Response;
import cn.techflowing.one.util.StringUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Editor Md 编辑器文件上传
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/12/28 1:22 上午
 */
@RestController
@RequestMapping("/blog/v1/editormd/upload/")
public class EditorMdFileUploadController {

    @PostMapping("image")
    public Object uploadImageInEditorMd(@RequestParam("editormd-image-file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return buildEditorMdUploadResult(false, "数据为空", null, null);
        }
        try (InputStream inputStream = file.getInputStream()) {
            String fileName = AliOssUtil.formatFileName(file.getOriginalFilename());
            String key = AliOssUtil.getImageUploadKey(fileName);
            boolean result = AliOssClient.get().uploadFile(AliOssConfig.BLOG_BUCKET_NAME, key, inputStream);
            return result ? buildEditorMdUploadResult(true, "ok", file.getOriginalFilename(), AliOssClient.getUrl(key)) :
                    buildEditorMdUploadResult(false, "上传失败", null, null);
        } catch (IOException e) {
            return buildEditorMdUploadResult(false, e.getMessage(), null, null);
        }
    }

    @PostMapping("file")
    public Object uploadFileInEditorMd(@RequestParam("editormd-file-file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return buildEditorMdUploadResult(false, "数据为空", null, null);
        }
        try (InputStream inputStream = file.getInputStream()) {
            String fileName = AliOssUtil.formatFileName(file.getOriginalFilename());
            String key = AliOssUtil.getFileUploadKey(fileName);
            boolean result = AliOssClient.get().uploadFile(AliOssConfig.BLOG_BUCKET_NAME, key, inputStream);
            return result ? buildEditorMdUploadResult(true, "ok", file.getOriginalFilename(), AliOssClient.getUrl(key)) :
                    buildEditorMdUploadResult(false, "上传失败", null, null);
        } catch (IOException e) {
            return Response.error(e.getMessage());
        }
    }

    /**
     * 构建 Editor Md 文件上传结果
     *
     * @param success 是否成功
     * @param msg     描述信息
     * @param alt     别名
     * @param url     存储地址
     */
    private Map<String, Object> buildEditorMdUploadResult(boolean success, String msg, String alt, String url) {
        Map<String, Object> map = new HashMap<>();
        map.put("success", success ? 1 : 0);
        map.put("message", msg);
        if (!StringUtil.isEmpty(alt)) {
            map.put("alt", alt);
        }
        if (!StringUtil.isEmpty(url)) {
            map.put("url", url);
        }
        return map;
    }
}
