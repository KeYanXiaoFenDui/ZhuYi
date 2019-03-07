package com.zy.domain;

import java.io.Serializable;
import java.util.Date;

public class ResponseInfo implements Serializable {
    private int id;//唯一标识
    private Date createTime;//创建时间
    private int status;//状态位
    private String content;//反馈信息
    private String type;//反馈类型（1场景，2需求）
    private int objectId;//所属需求/场景ID
    private int userId;//所属用户ID
    private int processStatus;//流程状态(1:待审核；2：已通过；3：已驳回)
    private int language;//语种（1：中文；2：英文）

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public Date getCreateTime() { return createTime; }

    public void setCreateTime(Date createTime) { this.createTime = createTime; }

    public int getStatus() { return status; }

    public void setStatus(int status) { this.status = status; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public int getObjectId() { return objectId; }

    public void setObjectId(int objectId) { this.objectId = objectId; }

    public int getUserId() { return userId; }

    public void setUserId(int userId) { this.userId = userId; }

    public int getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(int processStatus) {
        this.processStatus = processStatus;
    }

    public int getLanguage() {
        return language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }
}
