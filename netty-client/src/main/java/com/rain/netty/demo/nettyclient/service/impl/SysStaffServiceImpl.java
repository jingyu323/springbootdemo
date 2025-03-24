package com.rain.netty.demo.nettyclient.service.impl;

import com.rain.netty.demo.nettyclient.entity.SysStaff;
import com.rain.netty.demo.nettyclient.mapper.SysStaffMapper;
import com.rain.netty.demo.nettyclient.service.SysStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SysStaffServiceImpl implements SysStaffService {

    @Autowired
    private SysStaffMapper sysStaffMapper;

    @Override
    public List<SysStaff> findAll() {
        return sysStaffMapper.selectAll();
    }
}
