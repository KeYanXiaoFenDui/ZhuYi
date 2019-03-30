package com.zy.domain.vo;

import java.io.Serializable;
import java.util.Date;

public class StageRequestVo implements Serializable {
    private String idOrName;//场景名称
    private int country;//国家标识
    private int province;//省级标识
    private int city;//市级标识
    private int filmTypeId;//影视类型ID
    private int stageMainTypeId;//场景一级类型ID
    private int stageSubTypeId;//场景二级类型ID
    private int filmStyleId;//影视风格ID
    private int processStatus;//流程状态

    public String getIdOrName() {
        return idOrName;
    }

    public void setIdOrName(String idOrName) {
        this.idOrName = idOrName;
    }

    public int getCountry() {
        return country;
    }

    public void setCountry(int country) {
        this.country = country;
    }

    public int getProvince() {
        return province;
    }

    public void setProvince(int province) {
        this.province = province;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public int getFilmTypeId() {
        return filmTypeId;
    }

    public void setFilmTypeId(int filmTypeId) {
        this.filmTypeId = filmTypeId;
    }

    public int getStageMainTypeId() {
        return stageMainTypeId;
    }

    public void setStageMainTypeId(int stageMainTypeId) {
        this.stageMainTypeId = stageMainTypeId;
    }

    public int getStageSubTypeId() {
        return stageSubTypeId;
    }

    public void setStageSubTypeId(int stageSubTypeId) {
        this.stageSubTypeId = stageSubTypeId;
    }

    public int getFilmStyleId() {
        return filmStyleId;
    }

    public void setFilmStyleId(int filmStyleId) {
        this.filmStyleId = filmStyleId;
    }

    public int getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(int processStatus) {
        this.processStatus = processStatus;
    }

    @Override
    public String toString() {
        return "StageRequestVo{" +
                "idOrName='" + idOrName + '\'' +
                ", country=" + country +
                ", province=" + province +
                ", city=" + city +
                ", filmTypeId=" + filmTypeId +
                ", stageMainTypeId=" + stageMainTypeId +
                ", stageSubTypeId=" + stageSubTypeId +
                ", filmStyleId=" + filmStyleId +
                ", processStatus=" + processStatus +
                '}';
    }
}
