package cn.techflowing.one.blog.api;

import cn.techflowing.one.blog.xmind.model.XMind;
import cn.techflowing.one.blog.xmind.service.XMindService;
import cn.techflowing.one.common.response.Response;
import cn.techflowing.one.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 思维导图
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/9/4 4:03 下午
 */
@RestController
@RequestMapping("/blog/v1/xmind/")
public class XMindController {

    @Autowired
    XMindService mindService;

    @GetMapping("tree")
    public Response<Object> tree() {
        List<XMind> tree = mindService.queryXMindTree();
        return Response.success(tree);
    }

    @GetMapping("content")
    public Response<Object> content(@RequestParam(name = "key") String key) {
        return Response.success(mindService.getXMindMapContent(key));
    }

    /**
     * Json 数据转换为 Xmind 文件
     */
    @GetMapping("download")
    public ResponseEntity<Resource> downloadXMind(@RequestParam(name = "name") String name) {
        if (StringUtil.isEmpty(name)) {
            return ResponseEntity.badRequest().build();
        }
        Resource resource = mindService.getXMindFileResource(name);
        if (resource == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + StringUtil.encodeURL(name) + "\"")
                .body(resource);
    }
}
