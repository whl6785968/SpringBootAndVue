package com.sandalen.jwts.dao;

import com.sandalen.jwts.entity.DataPoint;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AlgoMapper {
    int staticsErrorDataPoint();
    List<DataPoint> getErrDataPoint();
}
