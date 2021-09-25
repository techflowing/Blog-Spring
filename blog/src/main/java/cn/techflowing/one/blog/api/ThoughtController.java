package cn.techflowing.one.blog.api;

import cn.techflowing.one.blog.thought.service.ThoughtService;
import cn.techflowing.one.common.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 随想录
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/9/23 12:36 上午
 */
@RestController
@RequestMapping("/blog/v1/thought/")
public class ThoughtController {

    @Autowired
    ThoughtService thoughtService;

    @GetMapping("list")
    public Response<Object> queryList(@RequestParam(name = "pageSize") int pageSize,
                                      @RequestParam(name = "page") int page) {
        return Response.success(thoughtService.queryList(page, pageSize));
    }

    @GetMapping("tags")
    public Response<Object> queryTags() {
        return Response.success(thoughtService.queryTags());
    }
}
