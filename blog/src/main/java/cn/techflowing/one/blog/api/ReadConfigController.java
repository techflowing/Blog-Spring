package cn.techflowing.one.blog.api;

import cn.techflowing.one.blog.config.Config;
import cn.techflowing.one.blog.config.ConfigService;
import cn.techflowing.one.common.response.Error;
import cn.techflowing.one.common.response.ErrorCode;
import cn.techflowing.one.common.response.Feature;
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
@RequestMapping("/blog/v1/config/")
public class ReadConfigController {

    @Autowired
    ConfigService configService;

    @GetMapping("get")
    public Response<Object> queryConfig(@RequestParam String name) {
        if (StringUtil.isEmpty(name)) {
            return Response.paramsError("name 为空");
        }
        Config config = configService.queryConfig(name);
        if (config == null) {
            return Response.fail(Error.of(Feature.COMMON, ErrorCode.RESULT_EMPTY, "未查到配置数据"));
        }
        return Response.success(config);
    }
}
