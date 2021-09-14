package cn.techflowing.one.blog.api;

import cn.techflowing.one.blog.statistic.model.StatisticEvent;
import cn.techflowing.one.blog.statistic.model.VisitorContent;
import cn.techflowing.one.blog.statistic.service.StatisticService;
import cn.techflowing.one.common.response.Response;
import cn.techflowing.one.util.DateUtil;
import cn.techflowing.one.util.IPUtils;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 埋点统计相关控制器
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/9/13 11:55 下午
 */
@RestController
@RequestMapping("/blog/v1/statistic/")
public class StatisticController {

    private static final String EVENT_VISITOR = "visitor";

    @Autowired
    StatisticService statisticService;

    @PostMapping("visitor")
    public Response<Object> visitor(HttpServletRequest request,
                                    @RequestParam(name = "scene") String scene,
                                    @RequestParam(name = "location") String location) {
        StatisticEvent<VisitorContent> event = new StatisticEvent<>();
        event.setDate(DateUtil.formatTimeToDate(System.currentTimeMillis()));
        event.setName(EVENT_VISITOR);
        event.setScene(scene);
        event.setLocation(location);
        event.setIp(IPUtils.getIpAddress(request));

        // 解析UserAgent字符串
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        Browser browser = userAgent.getBrowser();
        OperatingSystem operatingSystem = userAgent.getOperatingSystem();
        VisitorContent content = new VisitorContent();
        content.setDevice(operatingSystem.getDeviceType().toString());
        content.setPlatform(operatingSystem.getGroup().toString());
        content.setBrowser(browser.getGroup().toString());
        event.setContent(content);

        statisticService.addVisitorEvent(event);
        return Response.success();
    }

    @GetMapping("visitor/count")
    public Response<Object> queryVisitorStatistic() {
        return Response.success(statisticService.queryVisitorStatistic());
    }
}
