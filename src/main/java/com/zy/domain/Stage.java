package com.zy.domain;

import java.io.Serializable;
import java.util.Date;

public class Stage implements Serializable {
    private int id;//主键Id
    private Date createTime;//创建时间
    private int status;//状态(1有效,,,0无效)
    private String name;//场景名称
    private int country;//国家标识
    private int province;//省级标识
    private int city;//市级标识
    private String address;//具体地址
    private int film_type_id;//影视类型ID
    private int stage_main_type_id;//场景一级类型ID
    private int stage_sub_type_id;//场景二级类型ID
    private int film_style_id;//影视风格ID
    private int stage_area;//场景面积（1：0-100㎡；2：100-300㎡:3：300-500㎡；4：500-1000㎡:5：1000㎡以上）
    private int work_time;//可拍摄时间（1：工作日；2：周末；3：不限）
    private String parking;//停车位
    private int voltage;//电压（1：:220V；2:380V）
    private String other_note;//其他配套
    private String stage_desc;//场景描述
    private String pic_url;//图片链接
    private String video_url;//视频链接
    private String file_url;//文件链接
    private int response_info_id;//反馈信息ID
    private int process_status;//流程状态（1：）
    private int user_id;//所属用户的ID

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getFilm_type_id() {
        return film_type_id;
    }

    public void setFilm_type_id(int film_type_id) {
        this.film_type_id = film_type_id;
    }

    public int getStage_main_type_id() {
        return stage_main_type_id;
    }

    public void setStage_main_type_id(int stage_main_type_id) {
        this.stage_main_type_id = stage_main_type_id;
    }

    public int getStage_sub_type_id() {
        return stage_sub_type_id;
    }

    public void setStage_sub_type_id(int stage_sub_type_id) {
        this.stage_sub_type_id = stage_sub_type_id;
    }

    public int getFilm_style_id() {
        return film_style_id;
    }

    public void setFilm_style_id(int film_style_id) {
        this.film_style_id = film_style_id;
    }

    public int getStage_area() {
        return stage_area;
    }

    public void setStage_area(int stage_area) {
        this.stage_area = stage_area;
    }

    public int getWork_time() {
        return work_time;
    }

    public void setWork_time(int work_time) {
        this.work_time = work_time;
    }

    public String getParking() {
        return parking;
    }

    public void setParking(String parking) {
        this.parking = parking;
    }

    public int getVoltage() {
        return voltage;
    }

    public void setVoltage(int voltage) {
        this.voltage = voltage;
    }

    public String getOther_note() {
        return other_note;
    }

    public void setOther_note(String other_note) {
        this.other_note = other_note;
    }

    public String getStage_desc() {
        return stage_desc;
    }

    public void setStage_desc(String stage_desc) {
        this.stage_desc = stage_desc;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }

    public int getResponse_info_id() {
        return response_info_id;
    }

    public void setResponse_info_id(int response_info_id) {
        this.response_info_id = response_info_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getProcess_status() {
        return process_status;
    }

    public void setProcess_status(int process_status) {
        this.process_status = process_status;
    }
}
