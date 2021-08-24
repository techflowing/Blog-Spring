package cn.techflowing.one.blog.wiki.mapper;

import cn.techflowing.one.blog.wiki.model.WikiProject;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Wiki 项目Mapper
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/8/8 10:09 下午
 */
@Mapper
public interface WikiProjectMapper {

    String TABLE_NAME = "blog_wiki_project";

    @Select("select * from " + TABLE_NAME + " order by update_time desc")
    List<WikiProject> queryAllWikiProject();

    @Select("select * from " + TABLE_NAME + " where hash_key = #{key} limit 1")
    WikiProject queryWikiProject(String key);

    @Select("select id from " + TABLE_NAME + " where hash_key = #{key} limit 1")
    int queryWikiProjectId(String key);

    @Insert("insert into " + TABLE_NAME + "(name, description, thumb, hash_key) values (" +
            "#{name}, #{description}, #{thumb}, #{hashKey})")
    int createWikiProject(String name, String description, String thumb, String hashKey);

    @Delete("delete from " + TABLE_NAME + " where hash_key = #{hashKey} limit 1")
    int deleteWikiProject(String hashKey);

    @Update("update " + TABLE_NAME + " set name = #{name},description = #{description} ,thumb = #{thumb} " +
            "where hash_key = #{hashKey} limit  1")
    int updateWikiProject(String name, String description, String thumb, String hashKey);
}
