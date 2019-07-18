package com.sandalen.jwts.dao;

import java.util.List;

import com.sandalen.jwts.entity.Co2;
import com.sandalen.jwts.entity.Co2Example;
import org.apache.ibatis.annotations.Param;

public interface Co2Mapper {
    int countByExample(Co2Example example);

    int deleteByExample(Co2Example example);

    int deleteByPrimaryKey(Integer id);

    int insert(Co2 record);

    int insertSelective(Co2 record);

    List<Co2> selectByExample(Co2Example example);

    Co2 selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Co2 record, @Param("example") Co2Example example);

    int updateByExample(@Param("record") Co2 record, @Param("example") Co2Example example);

    int updateByPrimaryKeySelective(Co2 record);

    int updateByPrimaryKey(Co2 record);
}