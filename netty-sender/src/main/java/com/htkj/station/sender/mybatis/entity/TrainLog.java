package com.htkj.station.sender.mybatis.entity;

import lombok.Data;

import java.util.Date;

@Data
public class TrainLog {
    private String TrainSerial;
    private String ILog;
    private Date ITime;
}
