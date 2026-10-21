package com.expo.serviceimpl;

import com.expo.dao.StatusMasterDao;
import com.expo.dto.LoginDto;
import com.expo.entity.StatusMaster;
import com.expo.service.ReportGenerationService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportGenerationServiceImpl implements ReportGenerationService {
    @Autowired
    private StatusMasterDao statusMasterDao;


    @Override
    public byte[] get_excel() {

        Workbook workbook=new XSSFWorkbook();
        ByteArrayOutputStream outputStream =new ByteArrayOutputStream();

        Sheet sheet=workbook.createSheet("Loan");


        List<StatusMaster> status=statusMasterDao.findAll();
        Field[] fields=StatusMaster.class.getDeclaredFields();

        CellStyle headerStyle=workbook.createCellStyle();
        Font headerFont=workbook.createFont();
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);

        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);

            Cell cell = headerRow.createCell(i);
            cell.setCellValue(fields[i].getName());
            cell.setCellStyle(headerStyle); // Apply header style here
        }


        int num=1;
      for (StatusMaster statusMaster1: status){

          Row row =sheet.createRow(num++);
          row.createCell(0).setCellValue(statusMaster1.getStatus());
          row.createCell(1).setCellValue(statusMaster1.getId());


      }
     for ( int i=0;i<fields.length;i++){
sheet.autoSizeColumn(i);
        }

     try {
         workbook.write(outputStream);
         return outputStream.toByteArray();
     }
     catch (Exception e){
         e.printStackTrace();
         throw  new RuntimeException("Error generating execl");
     }

    }

    @Override
    public void get_pdf() {

    }
}
