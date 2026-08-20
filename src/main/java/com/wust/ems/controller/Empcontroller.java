package com.wust.ems.controller;

import com.wust.ems.pojo.Emp;
import com.wust.ems.pojo.PageBean;
import com.wust.ems.pojo.Result;
import com.wust.ems.service.impl.Empservice;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class Empcontroller {

    @Autowired
    private Empservice empservice;

    //分页查询
    //@RequestParam设置默认值
    //@DateTimeFormat用正则表达式设置格式
    @GetMapping
    public Result list(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name,
                       Short gender,
                       @DateTimeFormat(fallbackPatterns = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(fallbackPatterns = "yyyy-MM-dd") LocalDate end
                       ) {
        /*设置默认值，可以用@RequestParam("")来代替
        if(page == null) page=1;
        if(pagesize == null) pagesize=10;
        */
        log.info("分页查询，分页查询的参数：{},{},{},{},{},{}",page,pageSize,name,gender,begin,end);
        PageBean pageBean= empservice.list(page,pageSize,name,gender,begin,end);
        return Result.success(pageBean);
    }

    //删除单个员工
    //@DeleteMapping("/{id}")
    public Result delete(@Param("id") Integer id) {
        log.info("删除单个员工，员工id为：{}",id);
        empservice.delete(id);
        return Result.success();
    }

    //批量删除
    @DeleteMapping("/{ids}")
    public Result deletelist(@PathVariable List<Integer> ids) {
        log.info("删除多个员工，id分别为：{}",ids);
        empservice.deletelist(ids);
        return Result.success();
    }

    //增加员工
    @PostMapping
    public Result add(@RequestBody Emp emp) {
        log.info("增添员工，员工信息为：{}",emp);
        empservice.add(emp);
        return Result.success();
    }

    //修改员工信息
    @PutMapping
    public Result update(@RequestBody Emp emp) {
        log.info("修改员工信息为：{}",emp);
        empservice.update(emp);
        return Result.success();
    }

    //用id查询员工信息
    @GetMapping("/{id}")
    public Result select(@PathVariable Integer id) {
        log.info("根据id查询员工信息：{}",id);
        Emp p=empservice.select(id);
        return Result.success(p);
    }
}
