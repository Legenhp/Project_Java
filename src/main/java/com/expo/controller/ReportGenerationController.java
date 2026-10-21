package com.expo.controller;

import com.expo.service.ReportGenerationService;
import com.sun.mail.iap.ByteArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("report/apiversion")
public class ReportGenerationController {

    @Autowired
    ReportGenerationService reportGenerationService;

    @GetMapping(value = "get_excel")
    public ResponseEntity<?> get_excel(){
        byte[]  exel =reportGenerationService.get_excel();
        ByteArrayResource byteArrayResource=new ByteArrayResource(exel);
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION,
                                "attachment; filename=loans.xlsx")
                        .contentType(MediaType.parseMediaType(
                                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                        .contentLength(exel.length)
                        .body(byteArrayResource);
    }};
//
//    @GetMapping(value = "get_pdf")
//    public ResponseEntity<?> get_pdf(){
//     return  reportGenerationService.get_pdf();
//
//    };
//}
