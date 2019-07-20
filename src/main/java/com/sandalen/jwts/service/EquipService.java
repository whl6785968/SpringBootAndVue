package com.sandalen.jwts.service;

import com.sandalen.jwts.dao.CatagoryMapper;
import com.sandalen.jwts.dao.EquiplistMapper;
import com.sandalen.jwts.dao.ShowdataMapper;
import com.sandalen.jwts.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EquipService {

    @Autowired
    private EquiplistMapper equiplistMapper;

    @Autowired
    private ShowdataMapper showdataMapper;

    @Autowired
    private CatagoryMapper catagoryMapper;

    public List<Equiplist> getEquipList(EquiplistExample example){
        List<Equiplist> equiplists = equiplistMapper.selectByExample(example);
        return equiplists;
    }

    public Equiplist getEquipDetailById(int id){
        Equiplist equipDetailById = equiplistMapper.getEquipDetailById(id);
        return equipDetailById;
    }

    public Equiplist getPm10(String eno){
        Equiplist equiplist = equiplistMapper.selectHumi(eno);
        return equiplist;
    }

    public Equiplist getShowData(String eno){
        Equiplist equiplist = equiplistMapper.selectShowData(eno);
        return equiplist;
    }

    //查询每个设备的各类最新数据
    public void getData(String eno){
        List<Equiplist> equiplists = new ArrayList<>();
        Equiplist pm10 = equiplistMapper.selectPm10(eno);
        Equiplist pm25 = equiplistMapper.selectPm25(eno);
        Equiplist co2 = equiplistMapper.selectCo2(eno);
        Equiplist humi = equiplistMapper.selectHumi(eno);
        Equiplist illumi = equiplistMapper.selectIllumi(eno);
        Equiplist temper =  equiplistMapper.selectTemper(eno);

        String ename = pm10.getEname();

        equiplists.add(pm10);
        equiplists.add(pm25);
        equiplists.add(co2);
        equiplists.add(humi);
        equiplists.add(illumi);
        equiplists.add(temper);

        for(Equiplist equiplist : equiplists){
            if(equiplist!=null){
                Refmachine refmachine = equiplist.getRefmachines().get(0);
                String refName = refmachine.getName();
                Catagory catagory = refmachine.getCatagories().get(0);
                Integer cid = catagory.getId();
                String cname = catagory.getName();
                Date date = null;
                Double value = null;
                if(cname.equals("pm10")){
                    Pm10 pm101 = catagory.getPm10s().get(0);
                    date = pm101.getDdate();
                    value = pm101.getDvalue();
                }else if(cname.equals("pm2.5")){
                    Pm25 pm251 = catagory.getPm25s().get(0);
                    date = pm251.getDdate();
                    value = pm251.getDvalue();
                }else if(cname.equals("二氧化碳浓度")){
                    System.out.println(cname);
                    Co2 co21 = catagory.getCo2s().get(0);
                    date = co21.getDdate();
                    value = co21.getDvalue();
                }else if(cname.equals("温度")){
                    Temper temper1 = catagory.getTempers().get(0);
                    date = temper1.getDdate();
                    value = temper1.getDvalue();
                }else if(cname.equals("湿度")){
                    Humidity humidity = catagory.getHumidities().get(0);
                    date = humidity.getDdate();
                    value = humidity.getDvalue();
                }else if(cname.equals("光照度")){
                    Illumination illumination = catagory.getIlluminations().get(0);
                    date = illumination.getDdate();
                    value = illumination.getDvalue();
                }

                Showdata showdata = new Showdata();
                showdata.setEdname(ename);
                showdata.setEno(eno);
                showdata.setEdid(cid);
                showdata.setEref(refName);
                showdata.setCname(cname);
                showdata.setEdate(date);
                showdata.setEvalue(value);

                showdataMapper.insert(showdata);
            }
        }
    }

    public Equiplist selectRef(String eno){
        Equiplist equiplist = equiplistMapper.selectRef(eno);
        return equiplist;
    }

    public List<Catagory> selectCata(Map map){
        List<Catagory> equiplist = equiplistMapper.selectCata(map);
        return equiplist;
    }

    public List<Edata> getData(int cid,String eno,int rid){
        Catagory catagory = catagoryMapper.selectByPrimaryKey(cid);
        String name = catagory.getName();
        System.out.println(name);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("cname",name);
        map.put("eno",eno);
        map.put("rid",rid);
        List<Edata> data = equiplistMapper.getDataByEquip(map);
        return data;
    }

    public Refmachine getRefByEdid(int edid){
        Refmachine refByEdid = equiplistMapper.getRefByEdid(edid);
        return refByEdid;
    }

    public Catagory getCataByEdid(int edid){
        Catagory catagory = catagoryMapper.selectByPrimaryKey(edid);
        return catagory;
    }

    public List<Edata> getDataForExport(Map map){
        List<Edata> dataForExport = equiplistMapper.getDataForExport(map);
        return dataForExport;
    }




}
