package com.sandalen.jwts.dao;

import java.util.List;

import com.sandalen.jwts.entity.Equipdetail;
import com.sandalen.jwts.entity.EquipdetailExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface EquipdetailMapper {
    int countByExample(EquipdetailExample example);

    int deleteByExample(EquipdetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Equipdetail record);

    int insertSelective(Equipdetail record);

    List<Equipdetail> selectByExample(EquipdetailExample example);

    Equipdetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Equipdetail record, @Param("example") EquipdetailExample example);

    int updateByExample(@Param("record") Equipdetail record, @Param("example") EquipdetailExample example);

    int updateByPrimaryKeySelective(Equipdetail record);

    int updateByPrimaryKey(Equipdetail record);
}