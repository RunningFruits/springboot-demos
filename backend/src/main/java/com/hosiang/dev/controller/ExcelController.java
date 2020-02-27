package com.hosiang.dev.controller;

import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.demo.utils.ExcelUtil;
import com.hosiang.dev.model.EasyPOIModel;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("excel")
public class ExcelController {

    @RequestMapping("export")
    public String export(HttpServletRequest request, HttpServletResponse response) {

        // 查询数据,此处省略
        List<EasyPOIModel> list = new ArrayList<EasyPOIModel>();
        int count1 = 0;
        EasyPOIModel easyPOIModel11 = new EasyPOIModel(count1++, "信科", "张三", "男", 20);
        EasyPOIModel easyPOIModel12 = new EasyPOIModel(count1++, "信科", "李四", "男", 17);

        list.add(easyPOIModel11);
        list.add(easyPOIModel12);

        // 获取导出excel指定模版
        TemplateExportParams params = new TemplateExportParams();
        // 标题开始行
        params.setHeadingStartRow(0);
        // 标题行数
        params.setHeadingRows(2);
        // 设置sheetName，若不设置该参数，则使用得原本得sheet名称
        params.setSheetName("班级信息");

        params.setHeadingRows(2);
        params.setHeadingStartRow(2);
        params.setTempParams("t");
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("list", list);

        try {
            Workbook workbook = ExcelUtil.getWorkbook(params, data, "AttendanceLogsModel.xls");

            ExcelUtil.export(response, workbook, "easypoi-excel.xls");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //下载
        return null;
    }
}
