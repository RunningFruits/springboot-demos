package com.code.demo.backend.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class ExcelUtils {

    /**
     * excel通用导出 不能分sheet，具体例子参照AttendanceService.exportAttendanceInfos2方法
     *
     * @param excelName   excel名 暂时只支持英文
     * @param sheetName   sheet名
     * @param list        要导出的list
     * @param colMeans    数组 列名（中文）
     * @param classFields 数组 上面的列名对应的字段名 比如departName，userName等 如果是日期时间类型的，默认格式为yyyy-MM-dd HH:mm:ss,如果不喜欢这个格式，则写成：insertDate:yyyy-MM-dd,就是加个冒号加个格式
     * @param clazz       具体的entity类（不一定是entity 长得差不多的就行）
     * @param resp        response
     * @return -1 失败，1 成功
     */
    public <T> int makeExcel(String excelName, String sheetName, List<T> list, String[] colMeans, String[] classFields, Class<T> clazz, HttpServletResponse resp) {
        if (colMeans.length != classFields.length) {
            log.info("两个数组长度不一");
            return -1;
        }
        //创建工作簿
        XSSFWorkbook workBook = new XSSFWorkbook();
        //创建工作表
        XSSFSheet sheet = workBook.createSheet(sheetName);
        XSSFCellStyle style2 = workBook.createCellStyle();
        style2.setBorderBottom(BorderStyle.THIN);
        style2.setBorderLeft(BorderStyle.THIN);
        style2.setBorderRight(BorderStyle.THIN);
        style2.setBorderTop(BorderStyle.THIN);
        //创建首行
        XSSFRow row0 = sheet.createRow(0);
        //创建单元格
        XSSFCell cell;
        for (int i = 0; i < colMeans.length; i++) {
            cell = row0.createCell(i, XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(colMeans[i]);
            cell.setCellStyle(style2);
        }
        // 开始塞数据
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < list.size(); i++) {
            T data = list.get(i);
            XSSFRow tempRow = sheet.createRow(i + 1);
            for (int j = 0; j < classFields.length; j++) {
                String fieldName, methodname;
                String dateFormat = DateUtil.DATE_FORMAT_1;
                if (classFields[j].contains(":")) {
                    fieldName = classFields[j].split(":")[0];
                    dateFormat = classFields[j].split(":")[1];
                } else {
                    fieldName = classFields[j];
                }
                methodname = "get" + this.changeUp(fieldName);
                XSSFCell tempCell = tempRow.createCell(j, XSSFCell.CELL_TYPE_STRING);
                tempCell.setCellStyle(style2);
                // 获取名为methodname的方法
                // 找出该方法的type
                int k = 0;
                for (k = 0; k < fields.length; k++) {
                    if (fields[k].getName().equals(fieldName)) {
                        break;
                    }
                }
                try {
                    Method md = clazz.getDeclaredMethod(methodname);
                    Object obj = md.invoke(data);
                    if (obj == null) {
                        obj = "";
                    }
                    // 日期
                    if (fields[k].getType() == Timestamp.class) {
                        Timestamp t = (Timestamp) obj;
                        tempCell.setCellValue(DateUtil.timestampFormat(t, dateFormat));
                    } else if (fields[k].getType() == Date.class) {
                        Date t = (Date) obj;
                        tempCell.setCellValue(DateUtil.dateFormat(t, dateFormat));
                    } else if (fields[k].getType() == Long.class || fields[j].getType() == Integer.class || fields[j].getType() == Double.class || fields[j].getType() == Float.class) {
                        tempCell.setCellValue(obj + "");
                    } else {
                        tempCell.setCellValue(obj.toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            String temp = System.currentTimeMillis() + "";
            resp.reset();
            resp.setHeader("content-Type", "application/vnd.ms-excel");
            resp.setHeader("Content-Disposition", "attachment;filename=" + excelName + "_" + temp + ".xlsx");

            resp.setContentType("application/x-download;charset=UTF-8");
            OutputStream os = resp.getOutputStream();
            workBook.write(os);
            os.flush();
            os.close();
            workBook.close();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

        return 1;
    }

    /**
     * 将fname的第一个字母变为大写
     *
     * @param fname
     * @return Fname
     */
    private String changeUp(String fname) {
        int ml = fname.length();
        String newname = fname.substring(0, 1).toUpperCase()
                + fname.substring(1, ml);
        return newname;
    }

    public static Object changeValue(XSSFCell cell) {
        if (cell != null) {
            switch (cell.getCellType()) {
                case XSSFCell.CELL_TYPE_STRING:
                    return cell.getStringCellValue();
                case XSSFCell.CELL_TYPE_NUMERIC:
                    double d = cell.getNumericCellValue();
                    return (int) d + "";
            }
        }
        return null;
    }
}
