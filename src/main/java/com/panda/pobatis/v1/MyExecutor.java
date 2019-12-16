package com.panda.pobatis.v1;

import java.sql.*;

/**
 * 执行器：封装JDBC
 */
public class MyExecutor {
    public <T> T query(String sql, Object parameter) {
        Connection conn = null;
        Statement stmt = null;
        Blog blog = new Blog();

        try {
            //注册JDBC驱动
            Class.forName("com.mysql.jdbc.Driver");
            //打开连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/my-mybatis", "root", "123456");
            //执行查询
            stmt = conn.createStatement();
            //String.format()会将sql语句中%d的占位符替换成整型的参数
            ResultSet rs = stmt.executeQuery(String.format(sql, parameter));
            //获取结果集
            while (rs.next()) {
                Integer bid = rs.getInt("bid");
                String name = rs.getString("name");
                Integer authorId = rs.getInt("author_id");
                blog.setAuthorId(authorId);
                blog.setBid(bid);
                blog.setName(name);
            }
            System.out.println(blog);

            rs.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return (T)blog;
    }
}
