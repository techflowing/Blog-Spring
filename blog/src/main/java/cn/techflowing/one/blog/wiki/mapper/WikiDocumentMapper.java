package cn.techflowing.one.blog.wiki.mapper;

import cn.techflowing.one.blog.wiki.model.WikiDocument;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Wiki Document
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/8/11 1:03 上午
 */
@Mapper
public interface WikiDocumentMapper {

    String TABLE_NAME = "blog_wiki_document";

    @Select("select doc.id, doc.name, doc.type, doc.parent_id, doc.sort, doc.hash_key\n" +
            "from " + TABLE_NAME + " doc\n" +
            "         join " + WikiProjectMapper.TABLE_NAME + " project on doc.project_id = project.id\n" +
            "where project.hash_key = #{projectHashKey}")
    List<WikiDocument> queryDocumentByProjectKey(String projectHashKey);

    @Select("select content from " + TABLE_NAME + " where hash_key = #{key} limit 1;")
    String queryContentByHashKey(String key);
}
