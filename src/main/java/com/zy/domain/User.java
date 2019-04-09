package com.zy.domain;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private int id;//主键Id
    private int accStatus;//账号状态(0无效,,,,1待激活,,,2激活)
    private String account;//账号
    private String password;//密码
    private String userName;//用户昵称
    private String telephone;//手机号码
    private String mail;//邮箱
    private String openId;//微信Id
    private Date createTime;//注册时间
    private Date updateTime;//更新时间
    private int status;//状态(1有效,,,0无效)

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccStatus() {
        return accStatus;
    }

    public void setAccStatus(int accStatus) {
        this.accStatus = accStatus;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
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

}