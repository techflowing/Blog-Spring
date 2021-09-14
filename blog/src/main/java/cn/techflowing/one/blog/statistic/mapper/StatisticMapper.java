package cn.techflowing.one.blog.statistic.mapper;

import cn.techflowing.one.blog.statistic.model.StatisticEvent;
import cn.techflowing.one.blog.statistic.model.VisitorStatistic;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Mapper 类
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/9/14 11:56 下午
 */
@Mapper
public interface StatisticMapper {

    String EVENT_TABLE_NAME = "blog_statistic_event";
    String VISITOR_PV_TABLE_NAME = "blog_statistic_visitor_pv";
    String VISITOR_UV_TABLE_NAME = "blog_statistic_visitor_uv";

    @Insert("insert into " + EVENT_TABLE_NAME + "(name, scene, location, ip, content, date) values (" +
            "#{name}, #{scene}, #{location}, #{ip}, " +
            "#{content, typeHandler=cn.techflowing.one.common.mybatis.JsonTypeHandler}, #{date})")
    int insertEvent(StatisticEvent<?> event);

    @Select("select count(*) from " + EVENT_TABLE_NAME + " where name = 'visitor' " +
            "and scene = #{scene} " +
            "and location = #{location} " +
            "and ip = #{ip} " +
            "and date = #{date}")
    int visitorRecord(String date, String scene, String location, String ip);

    @Insert("insert into " + VISITOR_PV_TABLE_NAME + "(scene, location, pv) values (" +
            "#{scene}, #{location}, 1) on duplicate key update pv = pv + 1")
    int updateVisitorPv(String scene, String location);

    @Insert("insert into " + VISITOR_UV_TABLE_NAME + "(scene, location, uv) values (" +
            "#{scene}, #{location}, 1) on duplicate key update uv = uv + 1")
    int updateVisitorUv(String scene, String location);

    @Select("select uv.scene, uv.location, uv, pv from " + VISITOR_UV_TABLE_NAME + " uv " +
            "join " + VISITOR_PV_TABLE_NAME + " pv on uv.scene = pv.scene and uv.location = pv.location")
    List<VisitorStatistic> queryVisitorStatistic();
}
