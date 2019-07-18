package com.sandalen.jwts.dao;

import java.util.List;

import com.sandalen.jwts.entity.Refmachine;
import com.sandalen.jwts.entity.RefmachineExample;
import org.apache.ibatis.annotations.Param;

public interface RefmachineMapper {
    int countByExample(RefmachineExample example);

    int deleteByExample(RefmachineExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Refmachine record);

    int insertSelective(Refmachine record);

    List<Refmachine> selectByExample(RefmachineExample example);

    Refmachine selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Refmachine record, @Param("example") RefmachineExample example);

    int updateByExample(@Param("record") Refmachine record, @Param("example") RefmachineExample example);

    int updateByPrimaryKeySelective(Refmachine record);

    int updateByPrimaryKey(Refmachine record);
}