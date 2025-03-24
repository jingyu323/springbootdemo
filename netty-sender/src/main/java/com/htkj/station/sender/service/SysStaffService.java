package com.htkj.station.sender.service;


import com.htkj.station.sender.mybatis.entity.SysStaff;

import java.util.List;

public interface SysStaffService {
    List<SysStaff> findAll();
}
