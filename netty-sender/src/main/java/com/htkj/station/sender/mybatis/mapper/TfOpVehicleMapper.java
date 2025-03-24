package com.htkj.station.sender.mybatis.mapper;

<<<<<<< HEAD:netty-sender/src/main/java/com/htkj/station/sender/mybatis/mapper/TfOpVehicleMapper.java

import com.htkj.station.sender.mybatis.entity.TfOpVehicle;

=======
>>>>>>> ced8b7eb881f01411b521dcf729b569acbf6b5f0:Mybatis_generator/src/main/java/com/htkj/station/sender/entity/TfOpVehicleMapper.java
public interface TfOpVehicleMapper {
    int deleteByPrimaryKey(String vehicleSerial);

    int insert(TfOpVehicle record);

    int insertSelective(TfOpVehicle record);

    TfOpVehicle selectByPrimaryKey(String vehicleSerial);

    int updateByPrimaryKeySelective(TfOpVehicle record);

    int updateByPrimaryKey(TfOpVehicle record);
}