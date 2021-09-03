package cn.techflowing.one.blog.wiki.mapper;

import cn.techflowing.one.blog.wiki.model.WikiDocument;
import org.apache.ibatis.annotations.*;

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

    @Insert("insert into " + TABLE_NAME + "( project_id, name, type, parent_id, sort, hash_key) values (" +
            "#{projectId}, #{name}, #{type}, #{parentId}, #{sort}, #{hashKey})")
    int createNewDocument(int projectId, String name, int type, int parentId, int sort, String hashKey);

    @Update("update " + TABLE_NAME + " set name = #{name} where id = #{documentId}")
    int renameDocument(String name, int documentId);

    @Update("update " + TABLE_NAME + " set content = #{content} where hash_key = #{hashKey}")
    int updateDocumentContent(String content, String hashKey);

    @Delete("delete from " + TABLE_NAME + " where project_id = #{projectId}")
    int deleteAllDocument(int projectId);

    @DeleteProvider(type = MapperProvider.class, method = "deleteDocument")
    int deleteDocument(List<Integer> list);

    @Select("select id from " + TABLE_NAME + " where project_id = #{projectId} and parent_id = #{docId} order by sort")
    List<Integer> queryChildDocument(int projectId, int docId);

    @UpdateProvider(type = MapperProvider.class, method = "updateDocumentSort")
    int updateDocSort(List<Integer> list, int parentId);
}
