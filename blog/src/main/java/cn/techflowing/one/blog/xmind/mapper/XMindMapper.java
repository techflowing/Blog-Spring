package cn.techflowing.one.blog.xmind.mapper;

import cn.techflowing.one.blog.xmind.model.XMind;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Mapper 查询
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/9/4 3:53 下午
 */
@Mapper
public interface XMindMapper {

    String TABLE_NAME = "blog_xmind_map";

    @Select("select * from " + TABLE_NAME)
    List<XMind> queryAllXMind();

    @Select("select content from " + TABLE_NAME + " where hash_key = #{key} limit 1")
    Object queryContentByHashKey(String key);

    @Insert("insert into " + TABLE_NAME + "(name, type, parent_id, sort, hash_key) value (" +
            "#{name}, #{type}, #{parentId}, #{sort}, #{hashKey})")
    int create(String name, int type, int parentId, int sort, String hashKey);

    @DeleteProvider(type = XMindMapperProvider.class, method = "delete")
    int delete(List<Integer> list);

    @Update("update " + TABLE_NAME + " set name = #{name} where id = #{docId}")
    int rename(String name, int docId);

    @Update("update " + TABLE_NAME + " set content = #{content} where hash_key = #{hashKey}")
    int updateContent(String content, String hashKey);

    @Select("select id from " + TABLE_NAME + " where parent_id = #{id} order by sort")
    List<Integer> queryChildDocument(int id);

    @UpdateProvider(type = XMindMapperProvider.class, method = "updateXMindSort")
    int updateXMindSort(List<Integer> list, int parentId);
}
