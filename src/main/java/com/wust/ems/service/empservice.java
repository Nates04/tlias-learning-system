package com.wust.ems.service;

import com.wust.ems.pojo.Emp;
import com.wust.ems.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

public interface empservice {
  /*手动分页
  public List<Emp> list(Integer page, Integer pageSize);
  public Long pagecount();
   */

  //pagehelper分页
  public PageBean list(Integer page, Integer pageSize, String name, Short gender, LocalDate birthday,LocalDate end);

  public void delete(Integer id);

  public void deletelist(List<Integer> ids);

  public void add(Emp emp);

  public void update(Emp emp);

  public Emp select(Integer id);

  public Emp login(String username,String password);

}
