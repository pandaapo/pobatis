package com.panda.pobatis.v2.session;

import com.panda.pobatis.v1.mapper.BlogMapper;
import com.panda.pobatis.v2.executor.MyExecutor;

/**
 * PoBatis的API，提供给应用层使用
 */
public class DefaultSqlSession {
    private MyConfiguration configuration;
    private MyExecutor executor;

    public DefaultSqlSession(MyConfiguration configuration) {
        this.configuration = configuration;
        //根据全局配置决定是否使用缓存装饰
        this.executor = configuration.newExecutor();
    }

    public MyConfiguration getConfiguration() {
        return configuration;
    }

    public <T> T selectOne(String statement, Object[] parameter, Class pojo) {
        String sql = getConfiguration().getMappedStatement(statement);
        //打印代理对象时会自动调用toString()方法，触发invoke()
        return executor.query(sql, parameter, pojo);
    }

    public <T> T getMapper(Class<T> clazz) {
        return configuration.getMapper(clazz, this);
    }
}
