package com.htkj.station.sender.mybatis.entity;

import lombok.Data;

@Data
public class TfOpVehicle {
    private String vehicleSerial;

    private String trainSerial;

    private String vehicleId;

    private String vehicleSort;

    private String vehicleType;

    private String vehicleOrder;

    private String madeFactory;

    private String madeDate;

    private String conversion;

    private String vehicleOwner;

    private String axesDistance;

    private Integer midpartPicsNumber;

    private Long lockVersion;

    private Integer faultAuto;

    private Integer faultManul;

    private Integer carAxisNumber;

    private String grabInfo;

    private Integer cmspcarPicsNumber;

    private Integer dmspcarPicsNumber;

    private String posAbFlag;

    private Integer midpartcmPicsNumber;

    private Integer cmctcarPicsNumber;

    private Integer cmcarPicsNumber;

    private Integer dmcarPicsNumber;

    private String fileCreatedResult;

    private String fileCreatedRemark;

    private Integer ljbDb;

    private Integer qtZxjDb;

    private Integer zjbDb;

    private Integer htZxjDb;

    private Integer ljbCm;

    private Integer qtZxjCm;

    private Integer htZxjCm;

    private Integer zjbCm;

    public String getVehicleSerial() {
        return vehicleSerial;
    }

    public void setVehicleSerial(String vehicleSerial) {
        this.vehicleSerial = vehicleSerial == null ? null : vehicleSerial.trim();
    }

    public String getTrainSerial() {
        return trainSerial;
    }

    public void setTrainSerial(String trainSerial) {
        this.trainSerial = trainSerial == null ? null : trainSerial.trim();
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId == null ? null : vehicleId.trim();
    }

    public String getVehicleSort() {
        return vehicleSort;
    }

    public void setVehicleSort(String vehicleSort) {
        this.vehicleSort = vehicleSort == null ? null : vehicleSort.trim();
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType == null ? null : vehicleType.trim();
    }

    public String getVehicleOrder() {
        return vehicleOrder;
    }

    public void setVehicleOrder(String vehicleOrder) {
        this.vehicleOrder = vehicleOrder == null ? null : vehicleOrder.trim();
    }

    public String getMadeFactory() {
        return madeFactory;
    }

    public void setMadeFactory(String madeFactory) {
        this.madeFactory = madeFactory == null ? null : madeFactory.trim();
    }

    public String getMadeDate() {
        return madeDate;
    }

    public void setMadeDate(String madeDate) {
        this.madeDate = madeDate == null ? null : madeDate.trim();
    }

    public String getConversion() {
        return conversion;
    }

    public void setConversion(String conversion) {
        this.conversion = conversion == null ? null : conversion.trim();
    }

    public String getVehicleOwner() {
        return vehicleOwner;
    }

    public void setVehicleOwner(String vehicleOwner) {
        this.vehicleOwner = vehicleOwner == null ? null : vehicleOwner.trim();
    }

    public String getAxesDistance() {
        return axesDistance;
    }

    public void setAxesDistance(String axesDistance) {
        this.axesDistance = axesDistance == null ? null : axesDistance.trim();
    }

    public Integer getMidpartPicsNumber() {
        return midpartPicsNumber;
    }

    public void setMidpartPicsNumber(Integer midpartPicsNumber) {
        this.midpartPicsNumber = midpartPicsNumber;
    }

    public Long getLockVersion() {
        return lockVersion;
    }

    public void setLockVersion(Long lockVersion) {
        this.lockVersion = lockVersion;
    }

    public Integer getFaultAuto() {
        return faultAuto;
    }

    public void setFaultAuto(Integer faultAuto) {
        this.faultAuto = faultAuto;
    }

    public Integer getFaultManul() {
        return faultManul;
    }

    public void setFaultManul(Integer faultManul) {
        this.faultManul = faultManul;
    }

    public Integer getCarAxisNumber() {
        return carAxisNumber;
    }

    public void setCarAxisNumber(Integer carAxisNumber) {
        this.carAxisNumber = carAxisNumber;
    }

    public String getGrabInfo() {
        return grabInfo;
    }

    public void setGrabInfo(String grabInfo) {
        this.grabInfo = grabInfo == null ? null : grabInfo.trim();
    }

    public Integer getCmspcarPicsNumber() {
        return cmspcarPicsNumber;
    }

    public void setCmspcarPicsNumber(Integer cmspcarPicsNumber) {
        this.cmspcarPicsNumber = cmspcarPicsNumber;
    }

    public Integer getDmspcarPicsNumber() {
        return dmspcarPicsNumber;
    }

    public void setDmspcarPicsNumber(Integer dmspcarPicsNumber) {
        this.dmspcarPicsNumber = dmspcarPicsNumber;
    }

    public String getPosAbFlag() {
        return posAbFlag;
    }

    public void setPosAbFlag(String posAbFlag) {
        this.posAbFlag = posAbFlag == null ? null : posAbFlag.trim();
    }

    public Integer getMidpartcmPicsNumber() {
        return midpartcmPicsNumber;
    }

    public void setMidpartcmPicsNumber(Integer midpartcmPicsNumber) {
        this.midpartcmPicsNumber = midpartcmPicsNumber;
    }

    public Integer getCmctcarPicsNumber() {
        return cmctcarPicsNumber;
    }

    public void setCmctcarPicsNumber(Integer cmctcarPicsNumber) {
        this.cmctcarPicsNumber = cmctcarPicsNumber;
    }

    public Integer getCmcarPicsNumber() {
        return cmcarPicsNumber;
    }

    public void setCmcarPicsNumber(Integer cmcarPicsNumber) {
        this.cmcarPicsNumber = cmcarPicsNumber;
    }

    public Integer getDmcarPicsNumber() {
        return dmcarPicsNumber;
    }

    public void setDmcarPicsNumber(Integer dmcarPicsNumber) {
        this.dmcarPicsNumber = dmcarPicsNumber;
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