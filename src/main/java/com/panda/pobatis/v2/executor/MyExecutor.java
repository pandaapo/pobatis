package com.panda.pobatis.v2.executor;

public interface MyExecutor {
    <T> T query(String statement, Object[] parameter, Class pojo);
}
