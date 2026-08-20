package com.wust.ems.controller;

import com.wust.ems.pojo.Dept;
import com.wust.ems.pojo.Result;
import com.wust.ems.service.deptservice;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//开启日志
@Slf4j
//@RestController = @Controller + @ResponseBody 声明一个可注入类+处理HTTP请求直接返回json格式数据
@RestController
//同一系列请求可以用@RequastMapping来注释，以此来简写父路径
@RequestMapping("/depts")
public class Deptcontroller {

    //从IOC容器中获取bean对象
    @Autowired
    private deptservice deptservice;

    /*开启日志，可以用@Slf4j来代替
    private static Logger log= (Logger) LoggerFactory.getLogger(Deptcontroller.class
    */

    /*指定请求方式
    @RequestMapping(value = "/depts",method = RequestMethod.GET)
    method = RequestMethod.GET指定此路径的请求方式为GET，用@GetMapping代替
    */

    //get请求，查询所有
    @GetMapping
    public Result list(){
        //保存到日志中
        log.info("查询所有部门信息");

        List<Dept> list=deptservice.list();
        return Result.success(list);
    }

    //按照id查询
    @GetMapping("/{id}")
    //@PathVariable是SpringMVC用来从URL路径中提取参数的注解
    public Result selectById(@PathVariable Integer id) {
        log.info("根据ID查询部门：id={}", id);
        Dept dept = deptservice.selectById(id);
        return Result.success(dept);
    }

    //delete请求，删除部门
    @DeleteMapping("/{id}" )
    public Result delete(@PathVariable Integer id){
        log.info("根据id删除部门：{}",id);
        deptservice.delete(id);
        return Result.success();
    }

    //post请求，增加部门
    //@RequestBody注释告诉Spring前端传来的参数用JSON解析后把数据导入到类对象中
    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("增加部门信息：{}", dept);
        //前端传递的属性没有特殊设置时默认值为null，此时这些属性值要修改的话到service层修改
        deptservice.insert(dept);
        return Result.success();
    }

    //put请求，修改部门
    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info("修改了部门信息，修改为：{}", dept);
        deptservice.update(dept);
        return Result.success();
    }
}
