package com.panda.pobatis.v1;

import java.lang.reflect.Proxy;
import java.util.ResourceBundle;

/**
 * 配置类
 */
public class MyConfiguration {

    /**
     * 用来解析sql映射文件，即posql.properties。存放接口方法和sql语句的对应关系
     */
    public final static ResourceBundle sqlMappings;

    /**
     * 防止重复解析sql映射文件，写成静态方法块
     */
    static {
        sqlMappings = ResourceBundle.getBundle("v1_posql");
    }

    /**
     * 返回接口的代理类对象
     * @param clazz
     * @param sqlSession
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class clazz, MySqlSession sqlSession) {
        // 第二个参数是目标类，第三个参数是代理类
        return (T)Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{clazz}, new MyMapperProxy(sqlSession));
    }
}
