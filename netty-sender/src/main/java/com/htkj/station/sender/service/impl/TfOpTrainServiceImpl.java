package com.htkj.station.sender.service.impl;

import com.htkj.station.sender.mybatis.entity.TfOpTrain;
import com.htkj.station.sender.mybatis.entity.TrainLog;
import com.htkj.station.sender.mybatis.mapper.TfOpTrainMapper;
import com.htkj.station.sender.service.TfOpTrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
public class TfOpTrainServiceImpl implements TfOpTrainService {

    @Autowired
    private TfOpTrainMapper tfOpTrainMapper;

    @Override
    public List<TfOpTrain> queryAllTrain(Long indexId) {
        return tfOpTrainMapper.queryAllTrain(indexId);
    }
}
