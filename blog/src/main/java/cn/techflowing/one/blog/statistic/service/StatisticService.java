package cn.techflowing.one.blog.statistic.service;

import cn.techflowing.one.blog.statistic.mapper.StatisticMapper;
import cn.techflowing.one.blog.statistic.model.StatisticEvent;
import cn.techflowing.one.blog.statistic.model.VisitorContent;
import cn.techflowing.one.blog.statistic.model.VisitorStatistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 统计Service
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/9/14 12:39 上午
 */
@Service
public class StatisticService {

    @Autowired
    StatisticMapper statisticMapper;

    /**
     * 插入Visitor埋点事件，注意更新PV、UV 数据
     *
     * @param event 事件详情
     */
    public void addVisitorEvent(StatisticEvent<VisitorContent> event) {
        // 更新PV数据
        statisticMapper.updateVisitorPv(event.getScene(), event.getLocation());
        // 更新UV数据
        if (statisticMapper.visitorRecord(event.getDate(), event.getScene(), event.getLocation(), event.getIp()) == 0) {
            statisticMapper.updateVisitorUv(event.getScene(), event.getLocation());
        }
        // 插入事件记录
        statisticMapper.insertEvent(event);
    }

    /**
     * 查询访问统计数据
     */
    public List<VisitorStatistic> queryVisitorStatistic() {
        return statisticMapper.queryVisitorStatistic();
    }
}
