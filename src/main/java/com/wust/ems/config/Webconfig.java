package com.wust.ems.config;

import com.wust.ems.interceptor.Logincheckinterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//此类为拦截器的注册，filter为过滤器

@Configuration//配置类
public class Webconfig implements WebMvcConfigurer {

    //注入拦截器
    @Autowired
    private Logincheckinterceptor logincheckinterceptor;

    //配置注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logincheckinterceptor)
                .addPathPatterns("/**")//拦截的路径
                .excludePathPatterns("/login","/upload.html","/upload")//不拦截的路径
        ;
    }
}
