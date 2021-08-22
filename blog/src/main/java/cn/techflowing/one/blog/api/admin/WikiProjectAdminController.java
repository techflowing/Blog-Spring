package cn.techflowing.one.blog.api.admin;

import cn.techflowing.one.blog.wiki.model.WikiProject;
import cn.techflowing.one.blog.wiki.service.WikiProjectService;
import cn.techflowing.one.common.response.Response;
import cn.techflowing.one.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Wiki 项目管理
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/8/21 6:08 下午
 */
@RestController
@RequestMapping("/blog/v1/admin/wiki/project/")
public class WikiProjectAdminController {

    @Autowired
    WikiProjectService projectService;

    @PostMapping("add")
    public Response<Object> createWikiProject(@RequestBody WikiProject project) {
        if (project == null) {
            return Response.paramsError("Body 为空");
        }
        if (StringUtil.isEmpty(project.getName())
                || StringUtil.isEmpty(project.getDescription()) || StringUtil.isEmpty(project.getThumb())) {
            return Response.paramsError("字段缺失");
        }
        boolean result = projectService.createWikiProject(project.getName(), project.getDescription(), project.getThumb());
        return result ? Response.success() : Response.dbError();
    }

    @PostMapping("delete")
    public Response<Object> deleteWikiProject(@RequestBody WikiProject project) {
        if (project == null) {
            return Response.paramsError("Body 为空");
        }
        if (StringUtil.isEmpty(project.getHashKey())) {
            return Response.paramsError("HashKey 字段缺失");
        }
        boolean result = projectService.deleteWikiProject(project.getHashKey());
        return result ? Response.success() : Response.dbError();
    }

    @PostMapping("update")
    public Response<Object> updateWikiProject(@RequestBody WikiProject project) {
        if (project == null) {
            return Response.paramsError("Body 为空");
        }
        if (StringUtil.isEmpty(project.getHashKey())) {
            return Response.paramsError("HashKey 字段缺失");
        }
        if (StringUtil.isEmpty(project.getName())
                || StringUtil.isEmpty(project.getDescription()) || StringUtil.isEmpty(project.getThumb())) {
            return Response.paramsError("关键信息字段缺失");
        }
        boolean result = projectService.updateWikiProject(project);
        return result ? Response.success() : Response.dbError();
    }
}
