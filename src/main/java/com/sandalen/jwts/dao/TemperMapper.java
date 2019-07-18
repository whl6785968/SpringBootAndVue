package com.sandalen.jwts.dao;


import java.util.List;

import com.sandalen.jwts.entity.Temper;
import com.sandalen.jwts.entity.TemperExample;
import org.apache.ibatis.annotations.Param;

public interface TemperMapper {
    int countByExample(TemperExample example);

    int deleteByExample(TemperExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Temper record);

    int insertSelective(Temper record);

    List<Temper> selectByExample(TemperExample example);

    Temper selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Temper record, @Param("example") TemperExample example);

    int updateByExample(@Param("record") Temper record, @Param("example") TemperExample example);

    int updateByPrimaryKeySelective(Temper record);

    int updateByPrimaryKey(Temper record);
}