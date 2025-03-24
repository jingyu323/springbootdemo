package com.htkj.station.sender.mybatis.mapper;

import com.htkj.station.sender.mybatis.entity.TrainLog;

import java.util.List;

public interface TrainLogMapper {
    List<TrainLog> queryAllTrainLog();
}
