package cn.techflowing.one.blog.api.admin;

import cn.techflowing.one.blog.thought.model.Thought;
import cn.techflowing.one.blog.thought.service.ThoughtService;
import cn.techflowing.one.common.response.Response;
import cn.techflowing.one.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 随想录管理
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/9/23 12:36 上午
 */
@RestController
@RequestMapping("/blog/v1/admin/thought/")
public class ThoughtAdminController {

    @Autowired
    ThoughtService thoughtService;

    @PostMapping("create")
    public Response<Object> create(@RequestBody Thought body) {
        if (body == null) {
            return Response.emptyBody();
        }
        if (StringUtil.isEmpty(body.getTitle())) {
            return Response.paramsError("标题缺失");
        }
        if (StringUtil.isEmpty(body.getContent())) {
            return Response.paramsError("正文缺失");
        }
        if (StringUtil.isEmpty(body.getHtml())) {
            return Response.paramsError("HTML数据缺失");
        }
        return thoughtService.create(body) ? Response.success() : Response.dbError();
    }

    @PostMapping("update")
    public Response<Object> update(@RequestBody Thought body) {
        if (body == null) {
            return Response.emptyBody();
        }
        if (StringUtil.isEmpty(body.getTitle())) {
            return Response.paramsError("标题缺失");
        }
        if (StringUtil.isEmpty(body.getContent())) {
            return Response.paramsError("正文缺失");
        }
        if (StringUtil.isEmpty(body.getHtml())) {
            return Response.paramsError("HTML数据缺失");
        }
        if (StringUtil.isEmpty(body.getHashKey())) {
            return Response.paramsError("HashKey缺失");
        }
        return thoughtService.update(body) ? Response.success() : Response.dbError();
    }

    @DeleteMapping("delete")
    public Response<Object> delete(@RequestParam(name = "hashKey") String hashKey) {
        if (StringUtil.isEmpty(hashKey)) {
            return Response.paramsError("HashKey缺失");
        }
        return thoughtService.delete(hashKey) ? Response.success() : Response.dbError();
    }
}
