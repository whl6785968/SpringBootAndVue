package com.sandalen.jwts.controller;

import com.sandalen.jwts.beans.RespBean;
import com.sandalen.jwts.entity.Catagory;
import com.sandalen.jwts.entity.Edata;
import com.sandalen.jwts.service.EquipService;
import com.sandalen.jwts.utils.PoiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
public class DocumentController {
    @Autowired
    private EquipService equipService;

    @RequestMapping("/equip/exportEquipData")
    public ResponseEntity<byte[]> exportData(int edid){
        Catagory cataByEdid = equipService.getCataByEdid(edid);
        System.out.println(edid);
        String name = cataByEdid.getName();
        Map<String,Object> map = new HashMap<>();
        map.put("cname",name);
        map.put("edid",edid);

        String docName = name + edid +  ".xls";

        List<Edata> edatas = equipService.getDataForExport(map);

//        ResponseEntity<byte[]> responseEntity = PoiUtils.exportExcel(edatas, docName);

//        HttpStatus statusCode = responseEntity.getStatusCode();
//        System.out.println(statusCode.value());
//        if(statusCode.is2xxSuccessful()){
//            return RespBean.ok("导出成功");
//        }
//
//        return RespBean.error("导出失败");

        return PoiUtils.exportExcel(edatas,docName);

    }
}
