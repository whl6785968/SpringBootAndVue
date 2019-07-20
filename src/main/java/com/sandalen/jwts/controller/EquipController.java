package com.sandalen.jwts.controller;

import com.sandalen.jwts.beans.RespBean;
import com.sandalen.jwts.entity.*;
import com.sandalen.jwts.service.EquipService;
import com.sandalen.jwts.utils.PoiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RequestMapping("/equip")
@RestController
public class EquipController {
    @Autowired
    private EquipService equipService;

    @RequestMapping("/getList")
    public List<Equiplist> getEquipList(){
        EquiplistExample example = new EquiplistExample();
        List<Equiplist> equipList = equipService.getEquipList(example);
        return equipList;
    }

    @RequestMapping("/getDetail")
    public Equiplist getEquipDetailById(int id){
        System.out.println(id);
        Equiplist equipList = equipService.getEquipDetailById(id);
        return equipList;
    }

//    @RequestMapping("/equip/getData")
//    public List<Equiplist> getData(String eno){
//        List<Equiplist> data = equipService.getData(eno);
//        return data;
//    }

    @RequestMapping("/getPm10")
    public Equiplist getPm10(String eno){
        Equiplist pm10 = equipService.getPm10(eno);
        return pm10;
    }

    @RequestMapping("/insertShowData")
    public String insertShowData(){
        equipService.getData("00202440000000004");
        return "111";
    }

    @RequestMapping("/getShowData")
    public RespBean getShowData(String eno){
        Equiplist showData = equipService.getShowData(eno);
        return RespBean.ok("查询成功",showData);
    }

    @RequestMapping("/selectRef")
    public RespBean selectRef(String eno){
        Equiplist equiplist = equipService.selectRef(eno);
        return RespBean.ok("查询成功",equiplist);
    }

    @RequestMapping("/selectCata")
    public RespBean selectCata(String eno,int rid){
        Map<String, Object> map = new HashMap<>();
        map.put("eno",eno);
        map.put("rid",rid);
        List<Catagory> equiplist = equipService.selectCata(map);
        return RespBean.ok("成功",equiplist);
    }

    @RequestMapping("/getDetailData")
    public RespBean getDetailData(int cid,String eno,int rid){
        List<Edata> data = equipService.getData(cid, eno, rid);
        return RespBean.ok("成功了",data);
    }

    @RequestMapping("/getRefByEdid")
    public RespBean getRefByEdid(int edid){
        Refmachine refmachine = equipService.getRefByEdid(edid);
        return RespBean.ok("suc",refmachine);
    }





}
