package com.sas.typeHandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes({String.class})
@MappedJdbcTypes({JdbcType.VARCHAR})
public class FinancialLiabilitiesHandler extends BaseTypeHandler<String> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, String s, JdbcType jdbcType) throws SQLException {
        String processedValue = "false".equals(s)?"":s;
        preparedStatement.setString(i, processedValue);
    }

    @Override
    public String getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return "";
    }

    @Override
    public String getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return "";
    }

    @Override
    public String getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return "";
    }
}
