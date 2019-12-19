package com.panda.pobatis.v2.binding;

import com.panda.pobatis.v2.session.DefaultSqlSession;

import java.lang.reflect.Proxy;

/**
 * 用于产生MapperProxy代理类
 * @param <T>
 */
public class MapperProxyFactory<T> {
    private Class<T> mapperInterfaces; // mapper接口类
    private Class object; // 结果集需要映射成的类

    public MapperProxyFactory(Class<T> mapperInterfaces, Class object) {
        this.mapperInterfaces = mapperInterfaces;
        this.object = object;
    }

    public T newInstance(DefaultSqlSession sqlSession) {
        return (T)Proxy.newProxyInstance(mapperInterfaces.getClassLoader(), new Class[] { mapperInterfaces }, new MapperProxy(sqlSession, object));
    }
}
