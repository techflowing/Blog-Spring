package cn.techflowing.one.blog.wiki.mapper;

import cn.techflowing.one.blog.wiki.model.WikiProject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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

    @Select("select name, description, doc_count, thumb, hash_key from " + TABLE_NAME + " order by update_time desc")
    List<WikiProject> queryAllWikiProject();
}
