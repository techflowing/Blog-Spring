package cn.techflowing.one.blog.api.admin;

import cn.techflowing.one.blog.config.ConfigService;
import cn.techflowing.one.common.response.Response;
import cn.techflowing.one.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 配置信息读取
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/8/1 10:28 下午
 */
@RestController
@RequestMapping("/blog/v1/admin/config/")
public class WriteConfigController {

    @Autowired
    ConfigService configService;

    @PostMapping("set")
    public Response<Object> setConfig(@RequestParam String name,
                                      @RequestParam(required = false) String desc,
                                      @RequestBody Object body) {
        if (StringUtil.isEmpty(name)) {
            return Response.paramsError("name 为空");
        }
        if (body == null) {
            return Response.emptyBody();
        }
        boolean result = configService.updateOrCreateConfig(name, body, desc);
        return result ? Response.success() : Response.dbError();
    }

}
