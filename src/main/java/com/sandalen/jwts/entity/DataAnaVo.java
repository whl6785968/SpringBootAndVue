package com.sandalen.jwts.entity;

import java.util.List;

public class DataAnaVo {
    private int count;
    private List<DataPoint> dataPoint;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<DataPoint> getDataPoint() {
        return dataPoint;
    }

    public void setDataPoint(List<DataPoint> dataPoint) {
        this.dataPoint = dataPoint;
    }
}
