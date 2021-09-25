package cn.techflowing.one.blog.thought.mapper;

import cn.techflowing.one.blog.thought.model.Thought;
import cn.techflowing.one.common.mybatis.JsonTypeHandler;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 随想录 Mapper
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/9/22 1:02 上午
 */
@Mapper
public interface ThoughtMapper {

    String TABLE_NAME = "blog_thought";

    @Select("select * from " + TABLE_NAME + " order by create_time desc limit #{limit} offset #{offset}")
    @Results({
            @Result(column = "tag", property = "tag", typeHandler = JsonTypeHandler.class)
    })
    List<Thought> queryList(int limit, int offset);

    @Select("select count(*) from " + TABLE_NAME)
    int count();

    @Select("select * from " + TABLE_NAME + " where hash_key = #{hashKey} limit 1")
    Thought queryByHashKey(String hashKey);

    @Select("select tag from " + TABLE_NAME)
    @Results({
            @Result(column = "tag", property = "tag", typeHandler = JsonTypeHandler.class)
    })
    List<Thought> queryTag();

    @Insert("insert into " + TABLE_NAME + "(title, content, html, tag, hash_key) values (" +
            "#{title}, #{content}, #{html}, " +
            "#{tag, typeHandler=cn.techflowing.one.common.mybatis.JsonTypeHandler}, #{hashKey})")
    int create(String title, String content, String html, List<String> tag, String hashKey);

    @Update("update " + TABLE_NAME + " " +
            "set title = #{title}, " +
            "content = #{content}, " +
            "html = #{html}, " +
            "tag = #{tag, typeHandler=cn.techflowing.one.common.mybatis.JsonTypeHandler} " +
            "where hash_key = #{hashKey} limit 1")
    int update(String title, String content, String html, List<String> tag, String hashKey);

    @Delete("delete from " + TABLE_NAME + " where hash_key = #{hashKey} limit 1")
    int delete(String hashKey);
}
