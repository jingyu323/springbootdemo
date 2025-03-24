package com.htkj.station.sender.mybatis.mapper;


import com.htkj.station.sender.mybatis.entity.TfOpTrain;

import java.util.List;

public interface TfOpTrainMapper {
    int insert(TfOpTrain record);

    int insertSelective(TfOpTrain record);

    List<TfOpTrain> queryAllTrain(Long indexId);
}