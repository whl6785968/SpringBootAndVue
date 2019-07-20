package com.sandalen.jwts.utils;

import com.sandalen.jwts.entity.Edata;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class PoiUtils {
    public static ResponseEntity<byte[]> exportExcel(List<Edata> edatas,String docName){
        HttpHeaders headers = null;
        ByteArrayOutputStream baos = null;
        try{
            //创建excel 文档
            HSSFWorkbook sheets = new HSSFWorkbook();
            //创建摘要
            sheets.createInformationProperties();
            //获取文档信息，并配置
            DocumentSummaryInformation information = sheets.getDocumentSummaryInformation();
            //文档类别
            information.setCategory("some data");
            //设置文档管理员
            information.setManager("sandalen");
            //组织机构
            information.setCompany("com.sandalen");
            //获取摘要信息并配置
            SummaryInformation si = sheets.getSummaryInformation();
            //设置文档主题
            si.setSubject("sheet");
            si.setTitle("sheet1");
            si.setAuthor("sandalen");
            si.setComments("备注信息无");

            HSSFSheet data_sheet = sheets.createSheet("data sheet");
            HSSFCellStyle datecellStyle = sheets.createCellStyle();
            datecellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
            //创建标题的显示样式
            HSSFCellStyle headerStyle = sheets.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            data_sheet.setColumnWidth(0,5*256);
            data_sheet.setColumnWidth(1,12*256);
            data_sheet.setColumnWidth(2,5*256);
            data_sheet.setColumnWidth(3,5*256);

            HSSFRow headerRow = data_sheet.createRow(0);
            HSSFCell cell0 = headerRow.createCell(0);
            cell0.setCellValue("编号");
            cell0.setCellStyle(headerStyle);

            HSSFCell cell1 = headerRow.createCell(1);
            cell1.setCellValue("日期");
            cell1.setCellStyle(headerStyle);

            HSSFCell cell2 = headerRow.createCell(2);
            cell2.setCellValue("值");
            cell2.setCellStyle(headerStyle);

            HSSFCell cell3 = headerRow.createCell(3);
            cell3.setCellValue("数据点");
            cell3.setCellStyle(headerStyle);

            for(int i=0;i<edatas.size();i++){
                HSSFRow row = data_sheet.createRow(i + 1);
                Edata edata = edatas.get(i);
                row.createCell(0).setCellValue(edata.getId());
                HSSFCell dateCell = row.createCell(1);
                dateCell.setCellValue(edata.getDdate());
                dateCell.setCellStyle(datecellStyle);
                row.createCell(2).setCellValue(edata.getDvalue());
                row.createCell(3).setCellValue(edata.getCid());
            }
            headers = new HttpHeaders();

            headers.setContentDispositionFormData("attchment",
                    new String(docName.getBytes("UTF-8"),"iso-8859-1"));

            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

            baos = new ByteArrayOutputStream();
            sheets.write(baos);


        }catch (Exception e){

        }
        return new ResponseEntity<byte[]>(baos.toByteArray(),headers, HttpStatus.CREATED);
    }
}
