package com.zy.domain;

import java.io.Serializable;
import java.util.Date;

public class Category implements Serializable {
    private int id;//唯一标识
    private Date createTime;//创建时间
    private int status;//状态位
    private String name;//类型名称
    private int order;//权重
    private int level;//分类级别
    private int parentId;//所属父类ID（当分类级别为1时，该列值为空）
    private int type;//分类种类（1：影视剧类型；2：场景风格；3：场景类型）CategoryType
    private String nameEn;//类型名称（英文）

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

    public int getLevel() { return level; }

    public void setLevel(int level) { this.level = level; }

    public int getParentId() { return parentId; }

    public void setParentId(int parentId) { this.parentId = parentId; }

    public int getType() { return type; }

    public void setType(int type) { this.type = type; }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }
}
