package com.wust.ems.mapper;

import com.wust.ems.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface deptmapper {

    @Select("select * from dept")
    public List<Dept> list();

    //#{id}参数占位符，指定占位参数的值为方法传进来的id值
    @Delete("delete from dept where id=#{id}")
    public void delete(Integer id);

    //@Options获取数据库自动生成的主键ID，插入数据后，把数据库自动生成的ID回填到Java对象中
    @Options(keyProperty = "id" ,useGeneratedKeys = true)
    @Insert("INSERT INTO dept(name, create_time, update_time) VALUES(#{name}, #{createTime}, #{updateTime})")
    public void insert(Dept dept);

    @Update("update dept set name=#{name},update_time = #{updateTime} where id=#{id}")
    public void update(Dept dept);

    @Select("SELECT * FROM dept WHERE id = #{id}")
    public Dept selectById(Integer id);
}
