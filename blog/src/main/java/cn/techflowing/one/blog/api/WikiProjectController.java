package cn.techflowing.one.blog.api;

import cn.techflowing.one.blog.wiki.model.WikiProject;
import cn.techflowing.one.blog.wiki.service.WikiProjectService;
import cn.techflowing.one.common.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Wiki 项目管理
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/8/8 10:23 下午
 */
@RestController
@RequestMapping("/blog/v1/wiki/project")
public class WikiProjectController {

    @Autowired
    WikiProjectService wikiProjectService;

    @GetMapping("list")
    public Response<Object> queryWikiProjectList() {
        List<WikiProject> list = wikiProjectService.queryAllWikiProject();
        return Response.success(list);
    }
}
