package com.wust.ems.exceptiion;

//全局异常处理器

import com.wust.ems.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Allexception {

    @ExceptionHandler(Exception.class)
    public Result exception(Exception e) {
        e.printStackTrace();
        return Result.error("操作错误，请联系管理员处理，错误原因："+e.getMessage());
    }
}
