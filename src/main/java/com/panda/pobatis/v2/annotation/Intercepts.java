package com.panda.pobatis.v2.annotation;

import java.lang.annotation.*;

/**
 * 用于注解拦截器，指定拦截的方法。这里做了简化处理，源码中还指定了拦截的类。
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Intercepts {
    String value();
}
