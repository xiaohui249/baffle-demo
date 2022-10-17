package com.example.baffledemo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @version: java version 11
 * @Author: xiaohui
 * @description:
 * @date: 2022-10-15 17:05
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Baffle {

    //  默认启动挡板逻辑
    String enable() default "true";

    // 挡板实现类，必须与业务类实现相同的接口
    String className();
}
