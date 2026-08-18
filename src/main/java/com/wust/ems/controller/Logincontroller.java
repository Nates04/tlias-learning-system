package com.wust.ems.controller;

import com.wust.ems.pojo.Emp;
import com.wust.ems.pojo.Result;
import com.wust.ems.service.impl.Empservice;
import com.wust.ems.utils.Jwt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class Logincontroller {

    @Autowired
    private Empservice empservice;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        String username = emp.getUsername();
        String password = emp.getPassword();
        log.info("登录用户和密码为：{},{}",username,password);
        //获取数据库中员工信息
        Emp e=empservice.login(username,password);
        //登陆成功下发令牌
        if(e!=null){
            //用集合传入需要传给前端的信息
            Map<String,Object> map=new HashMap<>();
            map.put("id",e.getId());
            map.put("name",e.getName());
            map.put("username",e.getUsername());
            return Result.success(Jwt.getJWT(map));
        }
        //登陆失败返回登录信息
        return Result.error("no-Jwt");
    }
}
