package com.zy.domain;

import java.io.Serializable;
import java.util.Date;

public class CrewService implements Serializable {
    private int id;//主键Id
    private Date createTime;//创建时间
    private int status;//状态(1有效,,,0无效)
    private String name;//场景名称
    private int order;//权重
    private String service_logo_url;//图标链接
    private String service_pic_url;//服务图片链接
    private String description;//服务描述

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getService_logo_url() {
        return service_logo_url;
    }

    public void setService_logo_url(String service_logo_url) {
        this.service_logo_url = service_logo_url;
    }

    public String getService_pic_url() {
        return service_pic_url;
    }

    public void setService_pic_url(String service_pic_url) {
        this.service_pic_url = service_pic_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
