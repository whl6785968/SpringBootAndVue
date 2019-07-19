package com.sandalen.jwts.dao;

import java.util.List;

import com.sandalen.jwts.entity.Equiplist;
import com.sandalen.jwts.entity.Showdata;
import com.sandalen.jwts.entity.ShowdataExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ShowdataMapper {
    int countByExample(ShowdataExample example);

    int deleteByExample(ShowdataExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Showdata record);

    int insertSelective(Showdata record);

    List<Showdata> selectByExample(ShowdataExample example);

    Showdata selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Showdata record, @Param("example") ShowdataExample example);

    int updateByExample(@Param("record") Showdata record, @Param("example") ShowdataExample example);

    int updateByPrimaryKeySelective(Showdata record);

    int updateByPrimaryKey(Showdata record);
}