package com.sandalen.jwts.dao;

import java.util.List;

import com.sandalen.jwts.entity.Pm25;
import com.sandalen.jwts.entity.Pm25Example;
import org.apache.ibatis.annotations.Param;

public interface Pm25Mapper {
    int countByExample(Pm25Example example);

    int deleteByExample(Pm25Example example);

    int deleteByPrimaryKey(Integer id);

    int insert(Pm25 record);

    int insertSelective(Pm25 record);

    List<Pm25> selectByExample(Pm25Example example);

    Pm25 selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Pm25 record, @Param("example") Pm25Example example);

    int updateByExample(@Param("record") Pm25 record, @Param("example") Pm25Example example);

    int updateByPrimaryKeySelective(Pm25 record);

    int updateByPrimaryKey(Pm25 record);
}