package com.sandalen.jwts.entity;

import java.util.List;

public class Equiplist {
    private Integer id;

    private String ename;

    private String eno;

    private List<Refmachine> refmachines;

    private List<Showdata> showdata;

    private List<Equipdetail> equipdetailList;

    public List<Showdata> getShowdata() {
        return showdata;
    }

    public void setShowdata(List<Showdata> showdata) {
        this.showdata = showdata;
    }

    public List<Refmachine> getRefmachines() {
        return refmachines;
    }

    public void setRefmachines(List<Refmachine> refmachines) {
        this.refmachines = refmachines;
    }


    public List<Equipdetail> getEquipdetailList() {
        return equipdetailList;
    }

    public void setEquipdetailList(List<Equipdetail> equipdetailList) {
        this.equipdetailList = equipdetailList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename == null ? null : ename.trim();
    }

    public String getEno() {
        return eno;
    }

    public void setEno(String eno) {
        this.eno = eno == null ? null : eno.trim();
    }

}