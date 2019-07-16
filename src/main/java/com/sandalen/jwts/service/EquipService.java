package com.sandalen.jwts.service;

import com.sandalen.jwts.dao.EquiplistMapper;
import com.sandalen.jwts.entity.Equiplist;
import com.sandalen.jwts.entity.EquiplistExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipService {

    @Autowired
    private EquiplistMapper equiplistMapper;

    public List<Equiplist> getEquipList(EquiplistExample example){
        List<Equiplist> equiplists = equiplistMapper.selectByExample(example);
        return equiplists;
    }

    public Equiplist getEquipDetailById(int id){
        Equiplist equipDetailById = equiplistMapper.getEquipDetailById(id);
        return equipDetailById;
    }
}
