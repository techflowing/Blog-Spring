package cn.techflowing.one.blog.api.admin;

import cn.techflowing.one.blog.wiki.model.CreateDocumentBody;
import cn.techflowing.one.blog.wiki.model.DeleteDocumentBody;
import cn.techflowing.one.blog.wiki.model.RenameDocumentBody;
import cn.techflowing.one.blog.wiki.model.WikiDocument;
import cn.techflowing.one.blog.wiki.service.WikiDocumentService;
import cn.techflowing.one.common.mybatis.DbErrorException;
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
        try {
            boolean result = wikiDocumentService.createDocument(body);
            return result ? Response.success() : Response.dbError();
        } catch (DbErrorException e) {
            return Response.dbError();
        }
    }

    @PostMapping("rename")
    public Response<Object> renameDocument(@RequestBody RenameDocumentBody body) {
        if (body == null) {
            return Response.emptyBody();
        }
        if (StringUtil.isEmpty(body.getName()) || body.getDocumentId() <= 0) {
            return Response.paramsError("参数缺失");
        }
        boolean result = wikiDocumentService.renameDocument(body);
        return result ? Response.success() : Response.dbError();
    }

    @PostMapping("delete")
    public Response<Object> renameDocument(@RequestBody DeleteDocumentBody body) {
        if (body == null) {
            return Response.emptyBody();
        }
        if (StringUtil.isEmpty(body.getProjectKey()) || body.getDocumentId() == null || body.getDocumentId().isEmpty()) {
            return Response.paramsError("参数缺失");
        }
        try {
            boolean result = wikiDocumentService.deleteDocument(body);
            return result ? Response.success() : Response.dbError();
        } catch (DbErrorException e) {
            return Response.dbError();
        }
    }
}
