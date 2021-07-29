package cn.techflowing.one.common.mybatis;

import cn.techflowing.one.util.GsonUtil;
import cn.techflowing.one.util.StringUtil;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Json 数据处理
 *
 * @author techflowing@gmail.com
 * @version 1.0.0
 * @since 2021/6/29 11:27 上午
 */
public class JsonTypeHandler<T> extends BaseTypeHandler<T> {

    private Type type;

    public JsonTypeHandler() {
        type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, GsonUtil.toString(parameter));
    }

    @Override
    public T getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return fromJson(rs.getString(columnName));
    }

    @Override
    public T getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return fromJson(rs.getString(columnIndex));
    }

    @Override
    public T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return fromJson(cs.getString(columnIndex));
    }

    private T fromJson(String content) {
        if (StringUtil.isEmpty(content)) {
            return null;
        }
        return GsonUtil.convertJson(content, type);
    }
}
