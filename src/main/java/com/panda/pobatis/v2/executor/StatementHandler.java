package com.panda.pobatis.v2.executor;

import com.panda.pobatis.v2.parameter.ParameterHandler;
import com.panda.pobatis.v2.session.MyConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 封装JDBC Statement，用于操作数据库
 */
public class StatementHandler {
    private ResultSetHandler resultSetHandler = new ResultSetHandler();

    public <T> T query(String statement, Object[] parameter, Class pojo) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        Object result = null;

        try {
            conn = getConnection();
            preparedStatement = conn.prepareStatement(statement);
            ParameterHandler parameterHandler = new ParameterHandler(preparedStatement);
            parameterHandler.setParameters(parameter);
            preparedStatement.execute();
            result = resultSetHandler.handle(preparedStatement.getResultSet(), pojo);
            return (T) result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                conn = null;
            }
        }
        return null;
    }

    /**
     * 获取连接
     * @return
     */
    private Connection getConnection() {
        String driver = MyConfiguration.properties.getString("jdbc.driver");
        String url = MyConfiguration.properties.getString("jdbc.url");
        String username = MyConfiguration.properties.getString("jdbc.username");
        String password = MyConfiguration.properties.getString("jdbc.password");
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
