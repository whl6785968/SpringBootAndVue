package com.sandalen.jwts.entity;

import java.util.Date;

public class PublicMsgDetailBean {
    private Integer id;
    private String name;
    private String poi;
    private Date mdate;
    private Integer isEmer;
    private String other;
    private String uname;
    private Integer uid;
    private String imgList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoi() {
        return poi;
    }

    public void setPoi(String poi) {
        this.poi = poi;
    }

    public Date getMdate() {
        return mdate;
    }

    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    public Integer getIsEmer() {
        return isEmer;
    }

    public void setIsEmer(Integer isEmer) {
        this.isEmer = isEmer;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getImgList() {
        return imgList;
    }

    public void setImgList(String imgList) {
        this.imgList = imgList;
    }
}
