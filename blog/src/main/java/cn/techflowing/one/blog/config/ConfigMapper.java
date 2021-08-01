package cn.techflowing.one.blog.config;

import cn.techflowing.one.common.mybatis.JsonTypeHandler;
import org.apache.ibatis.annotations.*;

/**
 * 配置信息
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/8/1 10:11 下午
 */
@Mapper
public interface ConfigMapper {

    String TABLE_NAME = "blog_config";

    @Select("select name, content, `desc` from " + TABLE_NAME + " where name = #{name} limit 1")
    @Results({
            @Result(column = "content", property = "content", typeHandler = JsonTypeHandler.class)
    })
    Config getConfig(String name);

    @Insert("insert into " + TABLE_NAME + "(name, content, `desc`) values (#{name}, #{content, typeHandler=cn.techflowing.one.common.mybatis.JsonTypeHandler}, #{desc}) " +
            "on duplicate key update content = #{content, typeHandler=cn.techflowing.one.common.mybatis.JsonTypeHandler},`desc`=#{desc};")
    int updateConfig(String name, Object content, String desc);
}
