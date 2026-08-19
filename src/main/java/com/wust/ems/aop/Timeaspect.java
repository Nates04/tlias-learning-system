package com.wust.ems.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//记录操作时间

@Slf4j
@Component
//@Aspect//声明这个类是AOP类，定义了增强方法(记录日志，统计耗时等)
public class Timeaspect {

    //把所有相同的的切入点表达式抽取出来，以便后期的维护
    @Pointcut("execution(* com.wust.ems.service.*.*(..))")
    private void pt(){};

    //切入点表达式，指定返回值和统计什么方法
    @Around("pt()")
    public Object time(ProceedingJoinPoint joinPoint) throws Throwable {
        //方法运行前，的操作记录开始时间
        Long starttime = System.currentTimeMillis();

        //运行原始方法
        Object obj=joinPoint.proceed();

        //方法运行后的操作
        // 记录结束时间






        Long endtime = System.currentTimeMillis();
        log.info(joinPoint.getSignature()+"方法总耗时：{} ms", endtime-starttime);

        return obj;
    }
}
