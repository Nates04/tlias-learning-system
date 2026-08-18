package com.wust.ems.service.impl;

import com.wust.ems.mapper.deptlogmapper;
import com.wust.ems.pojo.Deptlog;
import com.wust.ems.service.deptlogservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Deptlogservice implements deptlogservice {

    @Autowired
    private deptlogmapper deptlogmapper;

    //开启一个新事务，不随着大的事务回滚而回滚，默认的是随着大事务的回滚而回滚的
    //接上若大事务回滚会导致该事务无法记录数据
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void insert(Deptlog deptlog) {
        deptlogmapper.insert(deptlog);
    }
}
