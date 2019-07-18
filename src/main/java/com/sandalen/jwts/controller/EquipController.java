package com.sandalen.jwts.controller;

import com.sandalen.jwts.entity.Equipdetail;
import com.sandalen.jwts.entity.Equiplist;
import com.sandalen.jwts.entity.EquiplistExample;
import com.sandalen.jwts.service.EquipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EquipController {
    @Autowired
    private EquipService equipService;

    @RequestMapping("/equip/getList")
    public List<Equiplist> getEquipList(){
        EquiplistExample example = new EquiplistExample();
        List<Equiplist> equipList = equipService.getEquipList(example);
        return equipList;
    }

    @RequestMapping("/equip/getDetail")
    public Equiplist getEquipDetailById(int id){
        System.out.println(id);
        Equiplist equipList = equipService.getEquipDetailById(id);
//        Equiplist equiplist = equipList.get(0);
//        List<Equipdetail> equipdetailList = equiplist.getEquipdetailList();

//        System.out.println(equipdetailList.size());
        return equipList;
    }

    @RequestMapping("/equip/getData")
    public List<Equiplist> getData(String eno){
        List<Equiplist> data = equipService.getData(eno);
        return data;
    }

}
