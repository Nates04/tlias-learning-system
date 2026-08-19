package com.wust.ems.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//自定义注解

//注解生效时间----在运行时生效
@Retention(RetentionPolicy.RUNTIME)
//注解加的位置----在方法上
@Target(ElementType.METHOD)
public @interface Log {
}
