package com.htkj.station.sender.service;

import com.htkj.station.sender.mybatis.entity.TfOpTrain;
import com.htkj.station.sender.mybatis.entity.TrainLog;


import java.util.List;

public interface TrainLogService {
    List<TrainLog> queryAllTrainLog();


}
