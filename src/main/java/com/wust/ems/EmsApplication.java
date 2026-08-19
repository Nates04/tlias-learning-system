package com.wust.ems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@ServletComponentScan//开启了对filter组件的支持
@EnableAspectJAutoProxy//开启拦截器
public class EmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmsApplication.class, args);
    }

}
