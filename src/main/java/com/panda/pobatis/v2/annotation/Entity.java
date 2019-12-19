package com.panda.pobatis.v2.annotation;

import java.lang.annotation.*;

/**
 * 用于注解mapper接口，以映射返回的实体类
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Entity {
    Class<?> value();
}
