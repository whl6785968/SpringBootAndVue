package com.sandalen.jwts.dao;

import com.sandalen.jwts.entity.Pm10;
import com.sandalen.jwts.entity.Pm10Example;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Pm10Mapper {
    int countByExample(Pm10Example example);

    int deleteByExample(Pm10Example example);

    int deleteByPrimaryKey(Integer id);

    int insert(Pm10 record);

    int insertSelective(Pm10 record);

    List<Pm10> selectByExample(Pm10Example example);

    Pm10 selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Pm10 record, @Param("example") Pm10Example example);

    int updateByExample(@Param("record") Pm10 record, @Param("example") Pm10Example example);

    int updateByPrimaryKeySelective(Pm10 record);

    int updateByPrimaryKey(Pm10 record);
}