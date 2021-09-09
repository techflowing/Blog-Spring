package cn.techflowing.one.blog.api.admin;

import cn.techflowing.one.blog.xmind.model.*;
import cn.techflowing.one.blog.xmind.model.export.XMindStruct;
import cn.techflowing.one.blog.xmind.service.XMindService;
import cn.techflowing.one.common.response.Error;
import cn.techflowing.one.common.response.ErrorCode;
import cn.techflowing.one.common.response.Feature;
import cn.techflowing.one.common.response.Response;
import cn.techflowing.one.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * XMind 文档管理
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/9/5 4:03 下午
 */
@RestController
@RequestMapping("/blog/v1/admin/xmind/")
public class XMindAdminController {

    @Autowired
    XMindService mindService;

    @PostMapping("create")
    public Response<Object> createXMind(@RequestBody CreateXMindBody body) {
        if (body == null) {
            return Response.emptyBody();
        }
        return mindService.createXMind(body) ? Response.success() : Response.dbError();
    }

    @PostMapping("delete")
    public Response<Object> deleteXMind(@RequestBody DeleteXMindBody body) {
        if (body == null) {
            return Response.emptyBody();
        }
        return mindService.delete(body) ? Response.success() : Response.dbError();
    }

    @PostMapping("rename")
    public Response<Object> renameXMind(@RequestBody RenameXMindtBody body) {
        if (body == null) {
            return Response.emptyBody();
        }
        return mindService.rename(body) ? Response.success() : Response.dbError();
    }

    @PostMapping("update")
    public Response<Object> updateXMind(@RequestBody UpdateXMindBody body) {
        if (body == null) {
            return Response.emptyBody();
        }
        return mindService.update(body) ? Response.success() : Response.dbError();
    }

    @PostMapping("drag")
    public Response<Object> dragXMind(@RequestBody DragXMindBody body) {
        if (body == null) {
            return Response.emptyBody();
        }
        return mindService.drag(body) ? Response.success() : Response.dbError();
    }

    /**
     * Json 数据转换为 Xmind 文件
     */
    @PostMapping("export-xmind")
    public Response<Object> exportXMind(@RequestBody XMindStruct body) {
        if (body == null) {
            return Response.emptyBody();
        }
        String url = mindService.exportXMind(body);
        return !StringUtil.isEmpty(url) ? Response.success(url) :
                Response.fail(Error.of(Feature.Blog.XMIND, ErrorCode.LOGIC_ERROR, "生成逻辑错误"));
    }
}
