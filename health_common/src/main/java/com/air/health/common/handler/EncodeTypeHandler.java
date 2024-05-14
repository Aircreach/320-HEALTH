package com.air.health.common.handler;// 可封装字段加密工具类提供加解密的功能，该工具类在应用初始或启动的时候设置密码等信息。

import com.air.health.common.util.AirPasswordEncoder;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.*;

public class EncodeTypeHandler extends BaseTypeHandler {


    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        String encodedPassword = new AirPasswordEncoder().encode(parameter.toString());
        ps.setString(i, encodedPassword);
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return rs.getString(columnName);
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return rs.getString(columnIndex);
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return cs.getString(columnIndex);
    }
}
