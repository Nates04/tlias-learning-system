package com.wust.ems.exceptiion;

//全局异常处理器

import com.wust.ems.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//不需要指定路径，只要Controller层有异常就会启动。
//@RestControllerAdvice是Spring提供的全局异常处理注解，用于统一处理Controller层抛出的异常
@RestControllerAdvice
public class Allexception {

    //@ExceptionHandler(XXX)配置的是指定类型的异常才会启动此类
    @ExceptionHandler(Exception.class)
    public Result exception(Exception e) {
        e.printStackTrace();
        return Result.error("操作错误，请联系管理员处理，错误原因："+e.getMessage());
    }
}
