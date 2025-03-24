package com.htkj.station.receiver.netty;

import io.netty.channel.Channel;

public class ReceiverNettyChannel {
    private String code;

    private String report_last_data;

    private String last_data;

    private volatile transient Channel channel;

    private String logPath;

    private String imagesPath;

    private String stationId;

    private String createDate;

    public String getStationId() {
        return this.stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getLogPath() {
        return this.logPath;
    }

    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }

    public String getImagesPath() {
        return this.imagesPath;
    }

    public void setImagesPath(String imagesPath) {
        this.imagesPath = imagesPath;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getReport_last_data() {
        return this.report_last_data;
    }

    public void setReport_last_data(String report_last_data) {
        this.report_last_data = report_last_data;
    }

    public Channel getChannel() {
        return this.channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}