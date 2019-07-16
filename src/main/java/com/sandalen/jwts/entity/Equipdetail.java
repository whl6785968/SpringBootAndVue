package com.sandalen.jwts.entity;

import java.util.Date;

public class Equipdetail {
    private Integer id;

    private Integer eid;

    private String edname;

    private Integer edid;

    private String eref;

    private Date edate;

    private Double evalue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public String getEdname() {
        return edname;
    }

    public void setEdname(String edname) {
        this.edname = edname == null ? null : edname.trim();
    }

    public Integer getEdid() {
        return edid;
    }

    public void setEdid(Integer edid) {
        this.edid = edid;
    }

    public String getEref() {
        return eref;
    }

    public void setEref(String eref) {
        this.eref = eref == null ? null : eref.trim();
    }

    public Date getEdate() {
        return edate;
    }

    public void setEdate(Date edate) {
        this.edate = edate;
    }

    public Double getEvalue() {
        return evalue;
    }

    public void setEvalue(Double evalue) {
        this.evalue = evalue;
    }
}