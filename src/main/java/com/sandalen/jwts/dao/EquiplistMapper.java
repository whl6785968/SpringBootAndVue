package com.sandalen.jwts.dao;


import java.util.List;
import java.util.Map;

import com.sandalen.jwts.entity.*;
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

    Equiplist selectPm10(String eno);

    Equiplist selectPm25(String eno);

    Equiplist selectCo2(String eno);

    Equiplist selectTemper(String eno);

    Equiplist selectHumi(String eno);

    Equiplist selectIllumi(String eno);

    Equiplist selectShowData(String eno);

    Equiplist selectRef(String eno);

    List<Catagory> selectCata(Map map);

    List<Edata> getDataByEquip(Map map);

    Refmachine getRefByEdid(int edid);

    List<Edata> getDataForExport(Map map);

    List<Equiplist> getAllEquipInfo();


}