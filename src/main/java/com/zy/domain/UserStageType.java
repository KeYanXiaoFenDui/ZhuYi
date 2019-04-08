package com.zy.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户场景分类
 */
public class UserStageType implements Serializable {
    private int id;//主键Id
    private String title;//身份名称
    private Date createTime;//创建时间
    private Date updateTime;//更新时间
    private int status;//状态(1有效,,,0无效)
    /**
     * 所属用户Id
     */
    private int userId;
    /**
     * 所属剧本Id
     */
    private int scenarioId;
    /**
     *场景分类名称
     */
    private String typeName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getScenarioId() {
        return scenarioId;
    }

    public void setScenarioId(int scenarioId) {
        this.scenarioId = scenarioId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
