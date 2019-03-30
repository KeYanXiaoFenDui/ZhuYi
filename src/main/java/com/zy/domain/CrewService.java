package com.zy.domain;

import java.io.Serializable;
import java.util.Date;

public class CrewService implements Serializable {
    private int id;//唯一标识
    private Date createTime;//创建时间
    private int status;//状态位
    private String name;//服务名称
    private int order;//权重
    private String serviceLogoUrl;//图标链接
    private String servicePicUrl;//服务图片
    private String description;//服务描述
    private String nameEn;//服务名称（英文）
    private String descriptionEn;//服务描述（英文）

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public Date getCreateTime() { return createTime; }

    public void setCreateTime(Date createTime) { this.createTime = createTime; }

    public int getStatus() { return status; }

    public void setStatus(int status) { this.status = status; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getOrder() { return order; }

    public void setOrder(int order) { this.order = order; }

    public String getServiceLogoUrl() { return serviceLogoUrl; }

    public void setServiceLogoUrl(String serviceLogoUrl) { this.serviceLogoUrl = serviceLogoUrl; }

    public String getServicePicUrl() { return servicePicUrl; }

    public void setServicePicUrl(String servicePicUrl) { this.servicePicUrl = servicePicUrl; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    @Override
    public String toString() {
        return "CrewService{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", status=" + status +
                ", name='" + name + '\'' +
                ", order=" + order +
                ", serviceLogoUrl='" + serviceLogoUrl + '\'' +
                ", servicePicUrl='" + servicePicUrl + '\'' +
                ", description='" + description + '\'' +
                ", nameEn='" + nameEn + '\'' +
                ", descriptionEn='" + descriptionEn + '\'' +
                '}';
    }
}
