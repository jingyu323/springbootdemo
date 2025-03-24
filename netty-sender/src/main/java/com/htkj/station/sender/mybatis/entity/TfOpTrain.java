package com.htkj.station.sender.mybatis.entity;

import java.util.Date;

public class TfOpTrain {
    private String trainSerial;

    private String trainId;

    private String stationId;

    private Date passTime;

    private String direction;

    private Integer vehicleNumber;

    private Integer freightVehicleNumber;

    private Integer carVehicleNumber;

    private Integer speed;

    private String trainSort;

    private Integer engineNumber;

    private String passKind;

    private String passState;

    private String viewState;

    private String picValidState;

    private Integer highestSpeed;

    private Integer lowestSpeed;

    private Integer equDetectNumber;

    private Integer indexId;

    private Integer detectNumber;

    private String dayNight;

    private String team;

    private String remark;

    private String imgReadyStatus;

    private Long lockVersion;

    private String trainStatus;

    private Integer vehicleExposure;

    private Integer photoExposure;

    private Integer vehicleBlack;

    private Integer photoBlack;

    private Integer vehicleLost;

    private Integer photoLost;

    private Integer vehicleDisoerder;

    private String fileCreatedResult;

    private String fileCreatedRemark;

    public String getTrainSerial() {
        return trainSerial;
    }

    public void setTrainSerial(String trainSerial) {
        this.trainSerial = trainSerial == null ? null : trainSerial.trim();
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId == null ? null : trainId.trim();
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId == null ? null : stationId.trim();
    }

    public Date getPassTime() {
        return passTime;
    }

    public void setPassTime(Date passTime) {
        this.passTime = passTime;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction == null ? null : direction.trim();
    }

    public Integer getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(Integer vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public Integer getFreightVehicleNumber() {
        return freightVehicleNumber;
    }

    public void setFreightVehicleNumber(Integer freightVehicleNumber) {
        this.freightVehicleNumber = freightVehicleNumber;
    }

    public Integer getCarVehicleNumber() {
        return carVehicleNumber;
    }

    public void setCarVehicleNumber(Integer carVehicleNumber) {
        this.carVehicleNumber = carVehicleNumber;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public String getTrainSort() {
        return trainSort;
    }

    public void setTrainSort(String trainSort) {
        this.trainSort = trainSort == null ? null : trainSort.trim();
    }

    public Integer getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(Integer engineNumber) {
        this.engineNumber = engineNumber;
    }

    public String getPassKind() {
        return passKind;
    }

    public void setPassKind(String passKind) {
        this.passKind = passKind == null ? null : passKind.trim();
    }

    public String getPassState() {
        return passState;
    }

    public void setPassState(String passState) {
        this.passState = passState == null ? null : passState.trim();
    }

    public String getViewState() {
        return viewState;
    }

    public void setViewState(String viewState) {
        this.viewState = viewState == null ? null : viewState.trim();
    }

    public String getPicValidState() {
        return picValidState;
    }

    public void setPicValidState(String picValidState) {
        this.picValidState = picValidState == null ? null : picValidState.trim();
    }

    public Integer getHighestSpeed() {
        return highestSpeed;
    }

    public void setHighestSpeed(Integer highestSpeed) {
        this.highestSpeed = highestSpeed;
    }

    public Integer getLowestSpeed() {
        return lowestSpeed;
    }

    public void setLowestSpeed(Integer lowestSpeed) {
        this.lowestSpeed = lowestSpeed;
    }

    public Integer getEquDetectNumber() {
        return equDetectNumber;
    }

    public void setEquDetectNumber(Integer equDetectNumber) {
        this.equDetectNumber = equDetectNumber;
    }

    public Integer getIndexId() {
        return indexId;
    }

    public void setIndexId(Integer indexId) {
        this.indexId = indexId;
    }

    public Integer getDetectNumber() {
        return detectNumber;
    }

    public void setDetectNumber(Integer detectNumber) {
        this.detectNumber = detectNumber;
    }

    public String getDayNight() {
        return dayNight;
    }

    public void setDayNight(String dayNight) {
        this.dayNight = dayNight == null ? null : dayNight.trim();
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team == null ? null : team.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getImgReadyStatus() {
        return imgReadyStatus;
    }

    public void setImgReadyStatus(String imgReadyStatus) {
        this.imgReadyStatus = imgReadyStatus == null ? null : imgReadyStatus.trim();
    }

    public Long getLockVersion() {
        return lockVersion;
    }

    public void setLockVersion(Long lockVersion) {
        this.lockVersion = lockVersion;
    }

    public String getTrainStatus() {
        return trainStatus;
    }

    public void setTrainStatus(String trainStatus) {
        this.trainStatus = trainStatus == null ? null : trainStatus.trim();
    }

    public Integer getVehicleExposure() {
        return vehicleExposure;
    }

    public void setVehicleExposure(Integer vehicleExposure) {
        this.vehicleExposure = vehicleExposure;
    }

    public Integer getPhotoExposure() {
        return photoExposure;
    }

    public void setPhotoExposure(Integer photoExposure) {
        this.photoExposure = photoExposure;
    }

    public Integer getVehicleBlack() {
        return vehicleBlack;
    }

    public void setVehicleBlack(Integer vehicleBlack) {
        this.vehicleBlack = vehicleBlack;
    }

    public Integer getPhotoBlack() {
        return photoBlack;
    }

    public void setPhotoBlack(Integer photoBlack) {
        this.photoBlack = photoBlack;
    }

    public Integer getVehicleLost() {
        return vehicleLost;
    }

    public void setVehicleLost(Integer vehicleLost) {
        this.vehicleLost = vehicleLost;
    }

    public Integer getPhotoLost() {
        return photoLost;
    }

    public void setPhotoLost(Integer photoLost) {
        this.photoLost = photoLost;
    }

    public Integer getVehicleDisoerder() {
        return vehicleDisoerder;
    }

    public void setVehicleDisoerder(Integer vehicleDisoerder) {
        this.vehicleDisoerder = vehicleDisoerder;
    }

    public String getFileCreatedResult() {
        return fileCreatedResult;
    }

    public void setFileCreatedResult(String fileCreatedResult) {
        this.fileCreatedResult = fileCreatedResult == null ? null : fileCreatedResult.trim();
    }

    public String getFileCreatedRemark() {
        return fileCreatedRemark;
    }

    public void setFileCreatedRemark(String fileCreatedRemark) {
        this.fileCreatedRemark = fileCreatedRemark == null ? null : fileCreatedRemark.trim();
    }
}