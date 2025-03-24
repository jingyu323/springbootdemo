package com.htkj.station.sender.service.impl;

import com.htkj.station.sender.mybatis.entity.SysStaff;
import com.htkj.station.sender.mybatis.mapper.SysStaffMapper;
import com.htkj.station.sender.service.SysStaffService;
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
