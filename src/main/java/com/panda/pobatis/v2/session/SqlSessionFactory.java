package com.panda.pobatis.v2.session;

/**
 * 会话工厂类，用于解析配置文件，产生SqlSession
 */
public class SqlSessionFactory {
    private MyConfiguration configuration;

    /**
     * build方法用于初始化MyConfiguration，解析配置文件的工作在MyConfiguration的构造函数中
     * @return
     */
    public SqlSessionFactory build() {
        configuration = new MyConfiguration();
        return this;
    }

    /**
     * 获取DefaulSqlSession
     * @return
     */
    public DefaultSqlSession openSqlSession() {
        return new DefaultSqlSession(configuration);
    }

}
