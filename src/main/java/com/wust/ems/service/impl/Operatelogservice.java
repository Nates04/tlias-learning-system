package com.wust.ems.service.impl;

import com.wust.ems.mapper.operatelogmapper;
import com.wust.ems.pojo.operateLog;
import com.wust.ems.service.operatelogservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Operatelogservice implements operatelogservice {

    @Autowired
    private operatelogmapper operatelogmapper;

    @Override
    public void insert(operateLog operateLog) {
        operatelogmapper.insert(operateLog);
    }
}
