package com.wust.ems.service;

import com.wust.ems.pojo.Dept;

import java.util.List;

public interface deptservice {
    List<Dept> list();
    void delete(Integer id);
    void insert(Dept dept);
    void update(Dept dept);
    Dept selectById(Integer id);
}
