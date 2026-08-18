package com.wust.ems.service.impl;

import com.wust.ems.mapper.deptmapper;
import com.wust.ems.pojo.Dept;
import com.wust.ems.service.deptservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class Deptservice implements deptservice {

    @Autowired
    private deptmapper deptmapper;

    //查询所有信息
    @Override
    public List<Dept> list(){
        return deptmapper.list();
    }

    //删除单个员工
    @Override
    public void delete(Integer id) {
        deptmapper.delete(id);
    }

    //新增员工
    @Override
    public void insert(Dept dept) {
        //设置时间(如果前端不传，后端自己生成)
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptmapper.insert(dept);
    }

    //修改员工
    @Override
    public void update(Dept dept){
        dept.setUpdateTime(LocalDateTime.now());
        deptmapper.update(dept);
    }

    //查询单个员工信息1
    @Override
    public Dept selectById(Integer id) {
        return deptmapper.selectById(id);
    }
}
