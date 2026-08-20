package com.wust.ems.mapper;

import com.wust.ems.pojo.Emp;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface empmapper {

    /*手动查询
    @Select("select count(*) from emp")
    public Long pagecount();

    @Select("select * from emp limit #{offset},#{pageSize}")
    public List<Emp> pagelist(@Param("offset")Integer offset,@Param("pageSize") Integer pageSize);
    */

    //pagehelper查询
    //@Select("select * from emp")
    public List<Emp> list(@Param("name") String name,
                          @Param("gender") Short gender,
                          @Param("begin") LocalDate begin,
                          @Param("end") LocalDate end,
                          @Param("offset") Integer offset,
                          @Param("pageSize") Integer pageSize);

    @Delete("delete from emp where id=#{id}")
    public void delete(@Param("id") Integer id);

    public void deletelist(@Param("ids") List<Integer> ids);

    @Insert("INSERT INTO emp (username, password, name, gender, image, job, " +
            "entrydate, dept_id, create_time, update_time) "
            //这是数据的字段列表名
            +
            "VALUES (#{username}, #{password}, #{name}, #{gender}, #{image}, #{job}," +
            " #{entrydate}, #{deptId}, #{createTime}, #{updateTime})")
            //这是后端实体类的属性名
    @Options(keyProperty = "id", useGeneratedKeys = true)
    public void add(Emp emp);

    public void update(Emp emp);

    @Select("select * from emp where id=#{id}")
    public Emp select(@Param("id") Integer id);

    @Select("select * from emp where username=#{username} and password=#{password}")
    public Emp login(@Param("username") String username, @Param("password") String password);

    @Delete("delete from emp where dept_id=#{deptid}")
    public void deletebydeptid(Integer deptid);
}
