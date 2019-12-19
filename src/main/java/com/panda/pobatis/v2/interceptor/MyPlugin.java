package com.panda.pobatis.v2.interceptor;

import com.panda.pobatis.v2.annotation.Intercepts;
import com.panda.pobatis.v2.plugin.Interceptor;
import com.panda.pobatis.v2.plugin.Invocation;
import com.panda.pobatis.v2.plugin.Plugin;

import java.util.Arrays;

/**
 * 自定义插件：拦截Executor的query方法。这里省去了指定需要拦截的类的代码
 */
@Intercepts("query")
public class MyPlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        String statement = (String) invocation.getArgs()[0];
        Object[] parameter = (Object[]) invocation.getArgs()[1];
        Class pojo = (Class) invocation.getArgs()[2];
        System.out.println("插件输出：SQL：[" +statement+ "]");
        System.out.println("插件输出：Parameters：" + Arrays.toString(parameter));
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }
}
