package com.panda.pobatis.v2.binding;

import com.panda.pobatis.v2.session.DefaultSqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 代理类：用于代理Mapper接口
 */
public class MapperProxy implements InvocationHandler {

    private DefaultSqlSession sqlSession;
    private Class object; //查询出来的结果集需要映射成的类

    public MapperProxy(DefaultSqlSession sqlSession, Class object) {
        this.sqlSession = sqlSession;
        this.object = object;
    }

    /**
     * 所有Mapper接口的方法调用都会走到这里
     * @param proxy ？？？这个是什么？？？
     * @param method 接口中定义的某方法
     * @param args 方法的入参
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String mapperInterface = method.getDeclaringClass().getName();
        String methodName = method.getName();
        String statementId = mapperInterface + "." + methodName;
        //如果根据接口类型+方法名能找到映射的SQL，则执行SQL
        if (sqlSession.getConfiguration().hasStatement(statementId)) {
            return sqlSession.selectOne(statementId, args, object);
        }
        //否则直接执行被代理对象的原方法
        return method.invoke(proxy, args);
    }
}
