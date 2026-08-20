package com.wust.ems.service.impl;

import com.wust.ems.anno.Log;
import com.wust.ems.mapper.deptmapper;
import com.wust.ems.mapper.empmapper;
import com.wust.ems.pojo.Dept;
import com.wust.ems.pojo.Deptlog;
import com.wust.ems.service.deptservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class Deptservice implements deptservice {

    @Autowired
    private deptmapper deptmapper;

    @Autowired
    private empmapper empmapper;

    @Autowired
    private Deptlogservice deptlogservice;

    //查询所有信息
    @Override
    public List<Dept> list(){
        return deptmapper.list();
    }

    //自定义注解，用于AOP检查
    @Log
    //删除部门及其对应的员工
    @Override
    /*事务管理，默认情况下只有运行时异常才会进行事务回滚
    rollbanFor---指定处理的异常种类
    pro
     */
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) {
        try {
            //删除部门
            deptmapper.delete(id);
            //删除对应员工
            empmapper.deletebydeptid(id);
        }finally {
            //增加删除日志
            Deptlog deptlog = new Deptlog();
            deptlog.setCreateTime(LocalDateTime.now());
            deptlog.setDescription("执行了删除操作，本次删除的是"+id+"号部门");
            deptlogservice.insert(deptlog);
        }
    }

    @Log
    //新增部门
    @Override
    public void insert(Dept dept) {
        //设置时间(如果前端不传，后端自己生成)
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptmapper.insert(dept);
    }

    @Log
    //修改部门
    @Override
    public void update(Dept dept){
        dept.setUpdateTime(LocalDateTime.now());
        deptmapper.update(dept);
    }

    //查询单个部门信息
    @Override
    public Dept selectById(Integer id) {
        return deptmapper.selectById(id);
    }
}
