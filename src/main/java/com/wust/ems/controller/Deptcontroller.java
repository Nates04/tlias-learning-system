package com.wust.ems.controller;

import com.wust.ems.mapper.empmapper;
import com.wust.ems.pojo.Dept;
import com.wust.ems.pojo.Result;
import com.wust.ems.service.deptservice;
import lombok.extern.slf4j.Slf4j;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j//开启日志
@RestController
//同一系列请求可以用@RequastMapping来注释，以此来简写路径
@RequestMapping("/depts")
public class Deptcontroller {

    @Autowired
    private deptservice deptservice1;

    /*开启日志，可以用@Slf4j来代替
    private static Logger log= (Logger) LoggerFactory.getLogger(Deptcontroller.class
    */

    /*指定请求方式
    @RequestMapping(value = "/depts",method = RequestMethod.GET)
    method = RequestMethod.GET指定此路径的请求方式为GET，用@GetMapping代替
    */
    //查询所有
    @GetMapping
    public Result list(){
        //保存到日志中
        log.info("查询所有部门信息");

        List<Dept> list=deptservice1.list();
        return Result.success(list);
    }

    //按照id查询
    @GetMapping("/{id}")
    public Result selectById(@PathVariable Integer id) {
        log.info("根据ID查询部门：id={}", id);
        Dept dept = deptservice1.selectById(id);
        return Result.success(dept);
    }

    //删除部门
    @DeleteMapping("/{id}" )
    public Result delete(@PathVariable Integer id){
        log.info("根据id删除部门：{}",id);
        deptservice1.delete(id);
        return Result.success();
    }

    //增加部门,用post请求，要加@RequestBody注释告诉Spring用JSON解析
    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("增加部门信息：{}", dept);
        deptservice1.insert(dept);
        return Result.success();
    }

    //修改部门
    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info("修改了部门信息，修改为：{}", dept);
        deptservice1.update(dept);
        return Result.success();
    }
}
