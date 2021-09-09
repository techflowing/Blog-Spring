package cn.techflowing.one.blog.xmind.mapper;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 动态SQL
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/9/5 4:16 下午
 */
public class XMindMapperProvider {

    public String delete(List<Integer> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("delete from " + XMindMapper.TABLE_NAME + " where id in (");
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

    public String updateXMindSort(List<Integer> list, int parentId) {
        StringBuilder sb = new StringBuilder();
        sb.append("update " + XMindMapper.TABLE_NAME + " set sort = case id ");
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
