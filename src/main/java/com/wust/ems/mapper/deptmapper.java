package com.wust.ems.mapper;

import com.wust.ems.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface deptmapper {

    @Select("select * from dept")
    public List<Dept> list();

    @Delete("delete from dept where id=#{id}")
    public void delete(Integer id);

    @Options(keyProperty = "id" ,useGeneratedKeys = true)
    @Insert("INSERT INTO dept(name, create_time, update_time) VALUES(#{name}, #{createTime}, #{updateTime})")
    public void insert(Dept dept);

    @Update("update dept set name=#{name},update_time = #{updateTime} where id=#{id}")
    public void update(Dept dept);

    @Select("SELECT * FROM dept WHERE id = #{id}")
    public Dept selectById(Integer id);
}
