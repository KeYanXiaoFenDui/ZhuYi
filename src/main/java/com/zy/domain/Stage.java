package com.zy.domain;

import java.io.Serializable;
import java.util.Date;

public class Stage implements Serializable {
    private int id;//唯一标识
    private Date createTime;//创建时间
    private int status;//状态(1有效,,,0无效)
    private String name;//场景名称
    private int country;//国家标识
    private int province;//省级标识
    private int city;//市级标识
    private String address;//详细地址
    private int filmTypeId;//影视类型ID
    private int stageMainTypeId;//场景一级类型ID
    private int stageSubTypeId;//场景二级类型ID
    private int stageStyleId;//影视风格ID
    private int stageArea;//场景面积（1：0-100㎡；2：100-300㎡:3：300-500㎡；4：500-1000㎡:5：1000㎡以上）
    private int workTime;//可拍摄时间（1：工作日；2：周末；3：不限）
    private int parking;//停车位
    private int voltage;//电压
    private String otherNote;//其他配套
    private String stageDesc;//场景描述
    private String picUrl;//图片链接
    private String videoUrl;//视频链接
    private String fileUrl;//文件链接
    private int responseInfoId;//反馈信息id
    private int processStatus;//流程状态
    private int userId;//发布者ID
    private String nameEn;//场景名称（英文）
    private String addressEn;//详细地址（英文）
    private String otherNoteEn;//其他配套（英文）
    private String stageDescEn;//场景描述（英文）
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public Date getCreateTime() { return createTime; }

    public void setCreateTime(Date createTime) { this.createTime = createTime; }

    public int getStatus() { return status; }

    public void setStatus(int status) { this.status = status; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getCountry() { return country; }

    public void setCountry(int country) { this.country = country; }

    public int getProvince() { return province; }

    public void setProvince(int province) { this.province = province; }

    public int getCity() { return city; }

    public void setCity(int city) { this.city = city; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public int getFilmTypeId() { return filmTypeId; }

    public void setFilmTypeId(int filmTypeId) { this.filmTypeId = filmTypeId; }

    public int getStageMainTypeId() { return stageMainTypeId; }

    public void setStageMainTypeId(int stageMainTypeId) { this.stageMainTypeId = stageMainTypeId; }

    public int getStageSubTypeId() { return stageSubTypeId; }

    public void setStageSubTypeId(int stageSubTypeId) { this.stageSubTypeId = stageSubTypeId; }

    public int getStageStyleId() {
        return stageStyleId;
    }

    public void setStageStyleId(int stageStyleId) {
        this.stageStyleId = stageStyleId;
    }

    public int getStageArea() { return stageArea; }

    public void setStageArea(int stageArea) { this.stageArea = stageArea; }

    public int getWorkTime() { return workTime; }

    public void setWorkTime(int workTime) { this.workTime = workTime; }

    public int getParking() { return parking; }

    public void setParking(int parking) { this.parking = parking; }

    public int getVoltage() { return voltage; }

    public void setVoltage(int voltage) { this.voltage = voltage; }

    public String getOtherNote() { return otherNote; }

    public void setOtherNote(String otherNote) { this.otherNote = otherNote; }

    public String getStageDesc() { return stageDesc; }

    public void setStageDesc(String stageDesc) { this.stageDesc = stageDesc; }

    public String getPicUrl() { return picUrl; }

    public void setPicUrl(String picUrl) { this.picUrl = picUrl; }

    public String getVideoUrl() { return videoUrl; }

    public void setVideoUrl(String videoUrl) { this.videoUrl = videoUrl; }

    public String getFileUrl() { return fileUrl; }

    public void setFileUrl(String fileUrl) { this.fileUrl = fileUrl; }

    public int getResponseInfoId() { return responseInfoId; }

    public void setResponseInfoId(int responseInfoId) { this.responseInfoId = responseInfoId; }

    public int getProcessStatus() { return processStatus; }

    public void setProcessStatus(int processStatus) { this.processStatus = processStatus; }

    public int getUserId() { return userId; }

    public void setUserId(int userId) { this.userId = userId; }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getAddressEn() {
        return addressEn;
    }

    public void setAddressEn(String addressEn) {
        this.addressEn = addressEn;
    }

    public String getOtherNoteEn() {
        return otherNoteEn;
    }

    public void setOtherNoteEn(String otherNoteEn) {
        this.otherNoteEn = otherNoteEn;
    }

    public String getStageDescEn() {
        return stageDescEn;
    }

    public void setStageDescEn(String stageDescEn) {
        this.stageDescEn = stageDescEn;
    }
}
