package com.zy.domain;

import java.io.Serializable;
import java.util.Date;

public class ResponseInfo implements Serializable {
    private int id;//主键Id
    private Date createTime;//创建时间
    private int status;//状态(1有效,,,0无效)
    private String content;//反馈信息neirong
    private String type;//反馈类型（1场景，2需求）
    private int object_id;//所属需求/场景的ID
    private int user_id;//所属用户ID

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getObject_id() {
        return object_id;
    }

    public void setObject_id(int object_id) {
        this.object_id = object_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
