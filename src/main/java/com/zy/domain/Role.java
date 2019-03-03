package com.zy.domain;

import java.io.Serializable;
import java.util.Date;

public class Role implements Serializable {
    private int id;//主键Id
    private String title;//身份名称
    private Date createTime;//创建时间
    private int status;//状态(1有效,,,0无效)

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}