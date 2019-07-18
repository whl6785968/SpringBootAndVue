package com.sandalen.jwts.dao;

import java.util.List;

import com.sandalen.jwts.entity.Humidity;
import com.sandalen.jwts.entity.HumidityExample;
import org.apache.ibatis.annotations.Param;

public interface HumidityMapper {
    int countByExample(HumidityExample example);

    int deleteByExample(HumidityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Humidity record);

    int insertSelective(Humidity record);

    List<Humidity> selectByExample(HumidityExample example);

    Humidity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Humidity record, @Param("example") HumidityExample example);

    int updateByExample(@Param("record") Humidity record, @Param("example") HumidityExample example);

    int updateByPrimaryKeySelective(Humidity record);

    int updateByPrimaryKey(Humidity record);
}