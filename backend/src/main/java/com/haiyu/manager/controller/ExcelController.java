package com.haiyu.manager.controller;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

@RestController
@RequestMapping("excel")
public class ExcelController {


    @RequestMapping("/download")
    public void download(HttpServletResponse response) throws Exception {
        //准备下载的文件名
        String filename = "employee.xlsx";
        response.setHeader("Content-disposition", filename);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); //mime类型
        response.setHeader("Content-disposition", "attachment;filename=" + filename);
        response.setHeader("Pragma", "No-cache");

        //1.创建一个Excel文件(内存中)
        SXSSFWorkbook wb = new SXSSFWorkbook();
        //2.创建一张表
        Sheet sheet = wb.createSheet("99乘法表");
        //3.创建行
        for (int i = 1; i <= 9; i++) {
            Row row = sheet.createRow(i - 1);
            //4.创建列(格子)
            for (int j = 1; j <= i; j++) {
                Cell cell = row.createCell(j - 1);
                //5.格子中加数据
                cell.setCellValue(i + "*" + j + "=" + (i * j));
            }
        }
        //从内存中写出来
        OutputStream out = response.getOutputStream();
        wb.write(out);
        out.close();
    }

}
