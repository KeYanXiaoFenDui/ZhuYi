package com.zy.domain;

import java.io.Serializable;
import java.util.Date;

public class UserRequest implements Serializable {
    private int id;//主键Id
    private int userId;//用户Id
    private int reqStatus;//需求状态(0已终止,,1待推荐,,,2推荐中)
    private String contactPeople;//剧组对接人
    private String job;//对接人职务
    private String telephone;//手机号码
    private String mail;//邮箱
    private String filmName;//影片剧名
    private int filmType;//影片类型
    private int playPlatform;//播放平台
    private int country;//国家标识
    private int province;//省级标识
    private int city;//市级标识
    private int seriesType;//影视剧类型
    private int stageType;//场景分类
    private int style;//风格
    private Date prepareDate;//筹备日期
    private Date beginDate;//拍摄日期
    private int continueDay;//置景天数
    private int shootDay;//拍摄天数
    private int groupPeopleNumber;//剧组人数
    private int groupCarNumber;//剧组车辆数
    private double budget;//制作预算
    private String description;//项目描述
    private String director;//导演
    private String performer;//主演
    private String prodCompany;//出品公司
    private String exeCompany;//制作公司
    private String otherReq;//其它需求
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

    public int getReqStatus() {
        return reqStatus;
    }

    public void setReqStatus(int reqStatus) {
        this.reqStatus = reqStatus;
    }

    public String getContactPeople() {
        return contactPeople;
    }

    public void setContactPeople(String contactPeople) {
        this.contactPeople = contactPeople;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public int getFilmType() {
        return filmType;
    }

    public void setFilmType(int filmType) {
        this.filmType = filmType;
    }

    public int getPlayPlatform() {
        return playPlatform;
    }

    public void setPlayPlatform(int playPlatform) {
        this.playPlatform = playPlatform;
    }

    public int getCountry() {
        return country;
    }

    public void setCountry(int country) {
        this.country = country;
    }

    public int getProvince() {
        return province;
    }

    public void setProvince(int province) {
        this.province = province;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public int getSeriesType() {
        return seriesType;
    }

    public void setSeriesType(int seriesType) {
        this.seriesType = seriesType;
    }

    public int getStageType() {
        return stageType;
    }

    public void setStageType(int stageType) {
        this.stageType = stageType;
    }

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }

    public Date getPrepareDate() {
        return prepareDate;
    }

    public void setPrepareDate(Date prepareDate) {
        this.prepareDate = prepareDate;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public int getContinueDay() {
        return continueDay;
    }

    public void setContinueDay(int continueDay) {
        this.continueDay = continueDay;
    }

    public int getShootDay() {
        return shootDay;
    }

    public void setShootDay(int shootDay) {
        this.shootDay = shootDay;
    }

    public int getGroupPeopleNumber() {
        return groupPeopleNumber;
    }

    public void setGroupPeopleNumber(int groupPeopleNumber) {
        this.groupPeopleNumber = groupPeopleNumber;
    }

    public int getGroupCarNumber() {
        return groupCarNumber;
    }

    public void setGroupCarNumber(int groupCarNumber) {
        this.groupCarNumber = groupCarNumber;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public String getProdCompany() {
        return prodCompany;
    }

    public void setProdCompany(String prodCompany) {
        this.prodCompany = prodCompany;
    }

    public String getExeCompany() {
        return exeCompany;
    }

    public void setExeCompany(String exeCompany) {
        this.exeCompany = exeCompany;
    }

    public String getOtherReq() {
        return otherReq;
    }

    public void setOtherReq(String otherReq) {
        this.otherReq = otherReq;
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