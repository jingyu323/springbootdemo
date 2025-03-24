package com.htkj.station.sender.service.impl;

import com.htkj.station.sender.mybatis.entity.TrainLog;
import com.htkj.station.sender.mybatis.mapper.TrainLogMapper;
import com.htkj.station.sender.service.TrainLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TrainLogServiceImpl implements TrainLogService {

    @Autowired
    private TrainLogMapper trainLogMapper;


    @Override
    public List<TrainLog> queryAllTrainLog() {
        return trainLogMapper.queryAllTrainLog();
    }
}
