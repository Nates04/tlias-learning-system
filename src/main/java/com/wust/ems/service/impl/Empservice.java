package com.wust.ems.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wust.ems.mapper.empmapper;
import com.wust.ems.pojo.Emp;
import com.wust.ems.pojo.PageBean;
import com.wust.ems.service.empservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class Empservice implements empservice {

    @Autowired
    private empmapper empmapper;

    /*手动分页
    @Override
    public List<Emp> list(Integer page, Integer pageSize) {
        int offset = (page - 1) * pageSize;
        return empmapper.pagelist(offset, pageSize);
    }


    @Override
    public Long pagecount() {
        return empmapper.pagecount();
    }
     */

    //pagehelper分页
    @Override
    public PageBean list(Integer page, Integer pageSize,
                         String name, Short gender,
                         LocalDate begin, LocalDate end) {
        int offset = (page - 1) * pageSize;
        //pageHelper会自动生成limit语句
        PageHelper.startPage(page, pageSize);
        List<Emp> list=empmapper.list(name,gender,begin,end,offset,pageSize);
        Page<Emp> p=(Page<Emp>)list;
        PageBean pageBean=new PageBean(p.getTotal(),p.getResult());
        return pageBean;
    }

    @Override
    public void delete(Integer id) {
        empmapper.delete(id);
    }

    @Override
    public void deletelist(List<Integer> ids) {
        empmapper.deletelist(ids);
    }

    @Override
    public void add(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        //设置默认值
        if (emp.getEntrydate() == null) {
            emp.setEntrydate(LocalDate.now());
        }
        if (emp.getPassword() == null) {
            emp.setPassword("123456");
        }
        empmapper.add(emp);
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empmapper.update(emp);
    }

    @Override
    public Emp select(Integer id) {
        return empmapper.select(id);
    }

    @Override
    public Emp login(String username,String password){
         return empmapper.login(username,password);
    }

}
