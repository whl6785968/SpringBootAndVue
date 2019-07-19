package com.sandalen.jwts.entity;

import java.util.Date;

public class Showdata {
    private Integer id;

    private String eno;

    private String edname;

    private String eref;

    private Integer edid;

    private String cname;

    private Date edate;

    private Double evalue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEno() {
        return eno;
    }

    public void setEno(String eno) {
        this.eno = eno == null ? null : eno.trim();
    }

    public String getEdname() {
        return edname;
    }

    public void setEdname(String edname) {
        this.edname = edname == null ? null : edname.trim();
    }

    public String getEref() {
        return eref;
    }

    public void setEref(String eref) {
        this.eref = eref == null ? null : eref.trim();
    }

    public Integer getEdid() {
        return edid;
    }

    public void setEdid(Integer edid) {
        this.edid = edid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
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