package com.zy.domain;

import java.io.Serializable;
import java.util.Date;

public class Recommendation implements Serializable {
    private int id;//主键Id
    private int userId;//用户Id
    private int reqId;//需求Id
    private int recommendStatus;//推荐状态(0未推荐,,,,1已推荐)
    private int stageType;//场景分类
    private String stageTitleCh;//场景分类中文名称
    private String stageTitleEn;//场景分类英文名称
    private int stageId;//场景Id
    private Date createTime;//创建时间
    private Date updateTime;//修改时间
    private int operateAdminId;//操作管理员Id
    private int status;//状态(1有效,,,0无效)

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getReqId() {
        return reqId;
    }

    public void setReqId(int reqId) {
        this.reqId = reqId;
    }

    public int getRecommendStatus() {
        return recommendStatus;
    }

    public void setRecommendStatus(int recommendStatus) {
        this.recommendStatus = recommendStatus;
    }

    public int getStageType() {
        return stageType;
    }

    public void setStageType(int stageType) {
        this.stageType = stageType;
    }

    public String getStageTitleCh() {
        return stageTitleCh;
    }

    public void setStageTitleCh(String stageTitleCh) {
        this.stageTitleCh = stageTitleCh;
    }

    public String getStageTitleEn() {
        return stageTitleEn;
    }

    public void setStageTitleEn(String stageTitleEn) {
        this.stageTitleEn = stageTitleEn;
    }

    public int getStageId() {
        return stageId;
    }

    public void setStageId(int stageId) {
        this.stageId = stageId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getOperateAdminId() {
        return operateAdminId;
    }

    public void setOperateAdminId(int operateAdminId) {
        this.operateAdminId = operateAdminId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}