package cn.techflowing.one.blog.api.admin;

import cn.techflowing.one.blog.wiki.model.CreateDocumentBody;
import cn.techflowing.one.blog.wiki.model.WikiDocument;
import cn.techflowing.one.blog.wiki.service.WikiDocumentService;
import cn.techflowing.one.common.response.Response;
import cn.techflowing.one.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Wiki 文档管理
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/8/22 6:06 下午
 */
@RestController
@RequestMapping("/blog/v1/admin/wiki/document/")
public class WikiDocumentAdminController {

    @Autowired
    WikiDocumentService wikiDocumentService;

    @GetMapping("tree")
    public Response<Object> queryWikiDocumentTree(@RequestParam(name = "projectKey") String projectKey) {
        List<WikiDocument> list = wikiDocumentService.queryDocumentTreeByProjectName(projectKey);
        return Response.success(list);
    }

    @PostMapping("create")
    public Response<Object> createDocument(@RequestBody CreateDocumentBody body) {
        if (body == null) {
            return Response.emptyBody();
        }
        if (StringUtil.isEmpty(body.getName()) || StringUtil.isEmpty(body.getProjectKey())) {
            return Response.paramsError("参数缺失");
        }
        boolean result = wikiDocumentService.createDocument(body);
        return result ? Response.success() : Response.dbError();
    }

}
