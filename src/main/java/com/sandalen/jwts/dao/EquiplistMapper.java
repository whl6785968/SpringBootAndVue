package com.sandalen.jwts.dao;


import java.util.List;

import com.sandalen.jwts.entity.Equiplist;
import com.sandalen.jwts.entity.EquiplistExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface EquiplistMapper {
    int countByExample(EquiplistExample example);

    int deleteByExample(EquiplistExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Equiplist record);

    int insertSelective(Equiplist record);

    List<Equiplist> selectByExample(EquiplistExample example);

    Equiplist selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Equiplist record, @Param("example") EquiplistExample example);

    int updateByExample(@Param("record") Equiplist record, @Param("example") EquiplistExample example);

    int updateByPrimaryKeySelective(Equiplist record);

    int updateByPrimaryKey(Equiplist record);

    Equiplist getEquipDetailById(int id);
}