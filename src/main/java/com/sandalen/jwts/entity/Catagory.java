package com.sandalen.jwts.entity;

import java.util.List;

public class Catagory {
    private Integer id;

    private String name;

    private Integer rid;

    private List<Pm10> pm10s;
    private List<Pm25> pm25s;
    private List<Co2> co2s;
    private List<Humidity> humidities;
    private List<Illumination> illuminations;
    private List<Temper> tempers;

    public List<Pm10> getPm10s() {
        return pm10s;
    }

    public void setPm10s(List<Pm10> pm10s) {
        this.pm10s = pm10s;
    }

    public List<Pm25> getPm25s() {
        return pm25s;
    }

    public void setPm25s(List<Pm25> pm25s) {
        this.pm25s = pm25s;
    }

    public List<Co2> getCo2s() {
        return co2s;
    }

    public void setCo2s(List<Co2> co2s) {
        this.co2s = co2s;
    }

    public List<Humidity> getHumidities() {
        return humidities;
    }

    public void setHumidities(List<Humidity> humidities) {
        this.humidities = humidities;
    }

    public List<Illumination> getIlluminations() {
        return illuminations;
    }

    public void setIlluminations(List<Illumination> illuminations) {
        this.illuminations = illuminations;
    }

    public List<Temper> getTempers() {
        return tempers;
    }

    public void setTempers(List<Temper> tempers) {
        this.tempers = tempers;
    }

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
        this.name = name == null ? null : name.trim();
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }
}