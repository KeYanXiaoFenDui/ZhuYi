package com.zy.domain;

import java.io.Serializable;
import java.util.Date;

public class RecommendationStage implements Serializable {
    private int id;//主键Id
    private int recStageTypeId;//推荐场景分类Id
    private int stageId;//场景Id
    private Date createTime;//创建时间
    private int operateAdminId;//操作管理员Id
    private int status;//状态(1有效,,,0无效)

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRecStageTypeId() {
        return recStageTypeId;
    }

    public void setRecStageTypeId(int recStageTypeId) {
        this.recStageTypeId = recStageTypeId;
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