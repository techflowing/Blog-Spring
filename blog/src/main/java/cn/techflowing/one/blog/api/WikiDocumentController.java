package cn.techflowing.one.blog.api;

import cn.techflowing.one.blog.wiki.model.WikiDocument;
import cn.techflowing.one.blog.wiki.service.WikiDocumentService;
import cn.techflowing.one.common.response.Response;
import cn.techflowing.one.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Wiki 文档管理
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/8/8 10:23 下午
 */
@RestController
@RequestMapping("/blog/v1/wiki/document/")
public class WikiDocumentController {

    @Autowired
    WikiDocumentService wikiDocumentService;

    @GetMapping("tree")
    public Response<Object> queryWikiDocumentTree(@RequestParam(name = "projectKey") String projectKey) {
        List<WikiDocument> list = wikiDocumentService.queryDocumentTreeByProjectName(projectKey);
        return Response.success(list);
    }

    @GetMapping("content")
    public Response<Object> queryContentByHashKey(@RequestParam(name = "key") String hashKey) {
        String content = wikiDocumentService.queryContentByHashKey(hashKey);
        if (StringUtil.isEmpty(content)) {
            return Response.success("");
        }
        return Response.success(content);
    }
}
