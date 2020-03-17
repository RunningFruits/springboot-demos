package cn.huanzi.qch.springbootsecurity.web.controller;


import cn.huanzi.qch.springbootsecurity.common.pojo.Result;
import cn.huanzi.qch.springbootsecurity.web.pojo.UserAnswer;
import cn.huanzi.qch.springbootsecurity.util.FileWithExcelUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

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

    @RequestMapping("/export")
    public Result export(HttpServletResponse response) {
        try {
            //模拟从数据库获取需要导出的数据
            List<UserAnswer> personList = new ArrayList<>();
            FileWithExcelUtil.exportExcel(personList, "客户信息表", "客户表", UserAnswer.class, "客户表.xls", response);
            return Result.ok("操作成功");
        } catch (Exception e) {
            return Result.error(500, "导出模版失败");
            // TODO: handle exception
        }
    }

    /**
     * 导入excel
     */
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public Result importExcel(@RequestParam("file") MultipartFile file) {
        try {
            List<UserAnswer> list = FileWithExcelUtil.importExcel(file, 1, 1, UserAnswer.class);
            System.out.println("导入数据一共【" + list.size() + "】行");

            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i).toString());
            }
            return Result.ok("操作成功");
        } catch (Exception e) {
            // TODO: handle exception
            return Result.error(500, "导入失败");
        }
    }

}
