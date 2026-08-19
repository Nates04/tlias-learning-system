package com.wust.ems.mapper;

import com.wust.ems.pojo.Deptlog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface deptlogmapper {

    @Insert("insert into deptlog(create_time, description) values(#{createTime},#{description})")
    public void insert(Deptlog log);
}
