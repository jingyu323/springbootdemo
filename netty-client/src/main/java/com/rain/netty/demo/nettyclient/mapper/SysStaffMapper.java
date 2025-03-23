package com.rain.netty.demo.nettyclient.mapper;


import com.rain.netty.demo.nettyclient.entity.SysStaff;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface SysStaffMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysStaff record);

    int insertSelective(SysStaff record);

    SysStaff selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysStaff record);

    int updateByPrimaryKey(SysStaff record);

    List<SysStaff> selectAll();
}