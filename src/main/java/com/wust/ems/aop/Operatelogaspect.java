package com.wust.ems.aop;

import com.alibaba.fastjson2.JSONObject;
import com.wust.ems.pojo.operateLog;
import com.wust.ems.service.impl.Operatelogservice;
import com.wust.ems.utils.Jwt;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

//记录增删改查操作日志

@Slf4j
@Aspect
@Component
public class Operatelogaspect {

    //获得当前请求的请求对象
    @Autowired
    HttpServletRequest request;

    @Autowired
    private Operatelogservice operatelogservice;

    @Around("@annotation(com.wust.ems.anno.Log)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        //运行方法前操作，开始时间
        Long startTime = System.currentTimeMillis();

        //运行方法
        Object obj=joinPoint.proceed();
        System.out.println("=== 准备插入日志 ===");

        //运行方法后操作，记录日志
        // 操作人ID---JWT中携带
        String jwt=request.getHeader("token");
        Claims claims=Jwt.parseJWT(jwt);
        Integer id=(Integer)claims.get("id");
        // 操作时间
        LocalDateTime nowtime= LocalDateTime.now();
        // 操作的类名
        String classname=joinPoint.getTarget().getClass().getName();
        // 操作的方法名
        String methodName=joinPoint.getSignature().getName();
        // 方法参数
        String methodParams=joinPoint.getArgs().toString();
        // 返回值---就是obj
        String str= JSONObject.toJSONString(obj);
        // 方法执行耗时，单位:ms
        Long endTime = System.currentTimeMillis();
        Long totalTime = endTime - startTime;
        //创建对象
        operateLog operatelog=new operateLog(
                null,
                id,
                nowtime,
                classname,
                methodName,
                methodParams,
                str,
                totalTime
        );
        //填写日志表
        operatelogservice.insert(operatelog);

        log.info("记录操日志:{}",operatelog);

        return obj;
    }
}
