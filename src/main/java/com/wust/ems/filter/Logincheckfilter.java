package com.wust.ems.filter;

import com.wust.ems.pojo.Result;
import com.wust.ems.utils.Jwt;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.alibaba.fastjson2.JSONObject;

//@WebFilter过滤器，全局拦截，JavaWeb规范（Servlet）提供的
//过滤器，因为在该项目中其作用和拦截器一样使用注释掉了，但是实际中都需要
//过滤器的过滤顺序可用隐式排序，即类名的字母大小排序，也可用显示排序@Order
//配置方式为：web.xml 或 @WebFilter

@Slf4j
//@WebFilter("/*")
public class Logincheckfilter implements Filter {
    /*可写可不写
    //初始化方法，只调用一次
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }
    */

    //过滤器方法，每次请求都要用到
    /*
    ServletRequest servletRequest---请求对象----封装请求信息（参数、头、路径等）
    ServletResponse servletResponse---响应对象---封装响应信息（输出、状态码等）
    FilterChain filterChain---被过滤器拦截到的方法
    */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //ServletRequest servletRequest请求对象----封装请求信息（参数、头、路径等）
        //ServletResponse servletResponse响应对象---封装响应信息（输出、状态码等）
        //把该对象强转为http对象
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //放行前的逻辑，拦截请求，只有符合逻辑才能放行
        //System.out.println("Logincheckfilter拦截到了请求");

        //获取请求url
        String url=request.getRequestURI();
        log.info("获取的请求路径为：{}",url);

        //判断请求是否为login路径
        if(url.contains("login")){
            log.info("该操作为登陆操作");
            //方法放行
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        //获取jwt令牌
        String jwt = request.getHeader("token");

        //前端负责jew校验失败后的登录跳转，在后端没有跳转逻辑
        //判断令牌是否存在
        if(jwt==null||jwt.equals("")){
            log.info("jwt令牌为空或者长度为0");
            Result result=Result.error("NOT-LOGIN");
            //返回的是JSON数据，需要强转，使用alibaba的fastjson依赖
            String notlogin=JSONObject.toJSONString(result);
            //直接调用响应对象的write方法，输出的是返回信息
            response.getWriter().write(notlogin);
            return;
        }

        //前端负责jew校验失败后的登录跳转，在后端发现跳转逻辑
        //解析token，如果解析失败，返回错误结果(未登录)
        try {
            Jwt.parseJWT(jwt);//解析失败就异常
        } catch (Exception e) {
            e.printStackTrace();
            log.info("jwt令牌失效，返回未登陆的信息");
            Result result=Result.error("NOT-LOGIN");
            //返回的是JSON数据，需要强转，使用alibaba的fastjson依赖
            String notlogin=JSONObject.toJSONString(result);
            response.getWriter().write(notlogin);
            return;
        }

        //开始放行
        log.info("jwt令牌合法");
        filterChain.doFilter(servletRequest, servletResponse);

        //放行后的逻辑，可以通过过滤器链来进行再次登录JWT令牌分析
        //System.out.println("LogincheckFilter结束了请求");
    }

    /*可写可不写
    //释放资源方法，只调用一次
    @Override
    public void destroy() {
        Filter.super.destroy();
    }
    */
}
