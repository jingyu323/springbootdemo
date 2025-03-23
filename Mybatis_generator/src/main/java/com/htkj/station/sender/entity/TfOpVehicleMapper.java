package com.htkj.station.sender.entity;

public interface TfOpVehicleMapper {
    int deleteByPrimaryKey(String vehicleSerial);

    int insert(TfOpVehicle record);

    int insertSelective(TfOpVehicle record);

    TfOpVehicle selectByPrimaryKey(String vehicleSerial);

    int updateByPrimaryKeySelective(TfOpVehicle record);

    int updateByPrimaryKey(TfOpVehicle record);
}