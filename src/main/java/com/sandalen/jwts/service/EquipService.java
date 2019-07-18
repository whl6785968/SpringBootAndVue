package com.sandalen.jwts.service;

import com.sandalen.jwts.dao.EquiplistMapper;
import com.sandalen.jwts.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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

    public List<Equiplist> getData(String eno){
        List<Equiplist> equiplists = new ArrayList<>();
        Equiplist pm10 = equiplistMapper.selectPm10(eno);
        Equiplist pm25 = equiplistMapper.selectPm25(eno);
        Equiplist co2 = equiplistMapper.selectCo2(eno);
        Equiplist humi = equiplistMapper.selectHumi(eno);
        Equiplist illumi = equiplistMapper.selectIllumi(eno);
        Equiplist temper =  equiplistMapper.selectTemper(eno);

        String ename = pm10.getEname();
        Date pm10Date = pm10.getRefmachines().get(0).getCatagories().get(0).getPm10s().get(0).getDdate();
        Double pm10Value = pm10.getRefmachines().get(0).getCatagories().get(0).getPm10s().get(0).getDvalue();



        equiplists.add(pm10);
        equiplists.add(pm25);
        equiplists.add(co2);
        equiplists.add(humi);
        equiplists.add(illumi);
        equiplists.add(temper);

        for(Equiplist equiplist : equiplists){
            Refmachine refmachine = equiplist.getRefmachines().get(0);
            String refName = refmachine.getName();
            Catagory catagory = refmachine.getCatagories().get(0);
            Integer cid = catagory.getId();
            String cname = catagory.getName();
            if(cname == "pm10"){
                Pm10 pm101 = catagory.getPm10s().get(0);
                Date date = pm101.getDdate();
                Double value = pm101.getDvalue();
            }

        }

        return equiplists;
    }
}
