package com.sandalen.jwts.dao;

import java.util.List;

import com.sandalen.jwts.entity.Catagory;
import com.sandalen.jwts.entity.CatagoryExample;
import org.apache.ibatis.annotations.Param;

public interface CatagoryMapper {
    int countByExample(CatagoryExample example);

    int deleteByExample(CatagoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Catagory record);

    int insertSelective(Catagory record);

    List<Catagory> selectByExample(CatagoryExample example);

    Catagory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Catagory record, @Param("example") CatagoryExample example);

    int updateByExample(@Param("record") Catagory record, @Param("example") CatagoryExample example);

    int updateByPrimaryKeySelective(Catagory record);

    int updateByPrimaryKey(Catagory record);
}