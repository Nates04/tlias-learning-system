package com.wust.ems.interceptor;

import com.alibaba.fastjson2.JSONObject;
import com.wust.ems.pojo.Result;
import com.wust.ems.utils.Jwt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//这个类为拦截器的定义，还需要注册
//该方法和拦截器的注册类Webconfig配套使用的，来完成所有指令都要完成的行为.如登陆校验等

@Slf4j
@Component
public class Logincheckinterceptor implements HandlerInterceptor {

    //视图渲染完毕后运行，最后运行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    //目标方法运行后运行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("请求执行完毕");
    }

    //目标方法运行前运行，为true放行
    //整体方法和filter方法一致，只不过放行是直接return true即可
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //log.info("拦截器拦截到请求");

        //获取请求url
        String url=request.getRequestURI();
        log.info("获取的请求路径为：{}",url);

        /*在config配置文件中设置了当操作为登陆时直接放行
        //判断请求是否为login路径
        if(url.contains("login")){
            log.info("该操作为登陆操作");
            return true;
        }
         */

        //获取jwt令牌
        String jwt = request.getHeader("token");

        //判断令牌是否存在
        if(jwt==null||jwt.equals("")){
            log.info("jwt令牌为空或者长度为0");
            Result result=Result.error("NOT-LOGIN");
            //返回的是JSON数据，需要强转，使用alibaba的fastjson依赖
            String notlogin= JSONObject.toJSONString(result);
            response.getWriter().write(notlogin);
            return false;
        }

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
            return false;
        }

        //开始放行
        log.info("jwt令牌合法");
        return true;
    }
}
