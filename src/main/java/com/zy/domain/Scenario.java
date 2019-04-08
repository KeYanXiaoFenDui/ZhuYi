package com.zy.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 剧本表
 */
public class Scenario implements Serializable {
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
     * 剧本名称
     */
    private String scenarioName;

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

    public String getScenarioName() {
        return scenarioName;
    }

    public void setScenarioName(String scenarioName) {
        this.scenarioName = scenarioName;
    }
}
