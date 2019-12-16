package com.panda.pobatis.v1;

/**
 * 该类提供给应用层使用
 */
public class MySqlSession {
    private MyConfiguration configuration;
    private MyExecutor executor;

    public MySqlSession(MyConfiguration configuration, MyExecutor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    /**
     * 调用Executor执行单条查询
     * @param statementId：sql语句的id和namespace（接口全类名）组成
     * @param parameter
     * @param <T> 该单条查询可能返回各种类型的结果，所以将返回类型定义成泛型
     * @return
     */
    public <T> T selectOne(String statementId, Object parameter) {
        String sql = MyConfiguration.sqlMappings.getString(statementId);
        return executor.query(sql, parameter);
    }

    /**
     * 获取一个代理对象
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class clazz){
        return configuration.getMapper(clazz, this);
    }
}
