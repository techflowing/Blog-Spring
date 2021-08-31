package cn.techflowing.one.blog.wiki.mapper;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 动态SQL
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/8/27 1:47 上午
 */
public class MapperProvider {

    public String deleteDocument(List<Integer> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("delete from " + WikiDocumentMapper.TABLE_NAME + " where id in (");
        MessageFormat mf = new MessageFormat("#'{'list[{0}]}");
        for (int i = 0; i < list.size(); i++) {
            sb.append(mf.format(new Object[]{i}));
            if (i < list.size() - 1) {
                sb.append(",");
            }
        }
        sb.append(")");
        return sb.toString();
    }

    public String updateDocumentSort(List<Integer> list, int parentId) {
        StringBuilder sb = new StringBuilder();
        sb.append("update " + WikiDocumentMapper.TABLE_NAME + " set sort = case id ");
        for (int i = 0; i < list.size(); i++) {
            sb.append("when ").append(list.get(i)).append(" then ").append(i).append(" ");
        }
        sb.append("end, parent_id = ")
                .append(parentId)
                .append(" ")
                .append("where id in (")
                .append(list.stream().map(String::valueOf).collect(Collectors.joining(",")))
                .append(")");
        return sb.toString();
    }
}
