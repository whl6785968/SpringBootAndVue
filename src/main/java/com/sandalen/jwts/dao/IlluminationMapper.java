package com.sandalen.jwts.dao;

import com.sandalen.jwts.entity.Illumination;
import com.sandalen.jwts.entity.IlluminationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IlluminationMapper {
    int countByExample(IlluminationExample example);

    int deleteByExample(IlluminationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Illumination record);

    int insertSelective(Illumination record);

    List<Illumination> selectByExample(IlluminationExample example);

    Illumination selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Illumination record, @Param("example") IlluminationExample example);

    int updateByExample(@Param("record") Illumination record, @Param("example") IlluminationExample example);

    int updateByPrimaryKeySelective(Illumination record);

    int updateByPrimaryKey(Illumination record);
}