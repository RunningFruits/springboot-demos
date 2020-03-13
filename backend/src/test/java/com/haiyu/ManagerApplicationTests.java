package com.haiyu;

import com.haiyu.manager.WebBootApplication;
import com.haiyu.manager.dao.BaseAdminUserMapper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {WebBootApplication.class})
public class ManagerApplicationTests {

    @Autowired
    private BaseAdminUserMapper baseAdminUserMapper;

    @Test
    public void testCreateExcel() throws Exception {
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
        FileOutputStream out = new FileOutputStream("99.xlsx");
        wb.write(out);
        out.close();
    }

    @Test
    public void readExcel() throws Exception {
        File file = new File("99.xlsx");
        FileInputStream fis = new FileInputStream(file);
        //1.读取一个Excel文件(内存中)
        Workbook wb = new XSSFWorkbook(fis);
        //2.拿到第个sheet表
        Sheet sheet = wb.getSheetAt(0);
        //3.拿到wb中的行(不要拿头部)
        int lastRowNum = sheet.getLastRowNum();
        for (int i = 1; i <= lastRowNum; i++) {
            Row row = sheet.getRow(i);
            //4.拿到每一列(格子)
            short lastCellNum = row.getLastCellNum();
            for (int j = 0; j < lastCellNum; j++) {
                Cell cell = row.getCell(j);
                System.out.print(cell.getStringCellValue() + " ");
            }
            System.out.println();
        }
    }

}
