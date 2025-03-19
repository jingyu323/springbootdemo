package com.htkj.station.sender.entity;

import com.htkj.station.sender.TFOpTrain;

public interface TFOpTrainMapper {
    int insert(TFOpTrain record);

    int insertSelective(TFOpTrain record);
}