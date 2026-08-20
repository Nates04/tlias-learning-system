package com.wust.ems.config;

import com.wust.ems.interceptor.Logincheckinterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//此类为拦截器的注册，filter为过滤器
//拦截器用的是spring框架
//配置方式WebMvcConfigurer
//注册拦截器，需拦截的请求路径，不需拦截的请求路径

@Configuration//配置类
public class Webconfig implements WebMvcConfigurer {

    //注入拦截器
    @Autowired
    private Logincheckinterceptor logincheckinterceptor;

    //注册并配置拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
                //配置拦截器
        registry.addInterceptor(logincheckinterceptor)
                //需拦截的请求路径
                .addPathPatterns("/**")
                //不拦截的请求路径，白名单
                .excludePathPatterns("/login","/upload.html","/upload")
        ;
    }
}
