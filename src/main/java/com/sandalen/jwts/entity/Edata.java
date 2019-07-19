package com.sandalen.jwts.entity;

import java.util.Date;

public class Edata {
    private Integer id;

    private Double dvalue;

    private Date ddate;

    private Integer cid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getDvalue() {
        return dvalue;
    }

    public void setDvalue(Double dvalue) {
        this.dvalue = dvalue;
    }

    public Date getDdate() {
        return ddate;
    }

    public void setDdate(Date ddate) {
        this.ddate = ddate;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }
}
