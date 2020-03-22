package com.init.demo.service.custom;

import com.init.demo.entity.custom.Excel;
import com.init.demo.repository.custom.ExcelRepository;
import com.microsoft.schemas.office.visio.x2012.main.CellType;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class ExcelService {

    @Autowired
    private ExcelRepository excelRepository;

    @Transactional(rollbackFor = Exception.class)
    public void getExcel(MultipartFile file) throws Exception {
        List<Excel> list = new ArrayList<Excel>();

        //1.得到上传的表
        Workbook workbook2 = WorkbookFactory.create(file.getInputStream());
        //2、获取sheet1工作表
        Sheet sheet2 = workbook2.getSheet("sheet1");
        //获取表的总行数
        int num = sheet2.getLastRowNum();
        //总列数
        int col = sheet2.getRow(0).getLastCellNum();

        //遍历excel每一行
        for (int j = 1; j < num; j++) {
            Row row1 = sheet2.getRow(j);

            //获取表中第i行，第2列的单元格
            Cell cell3 = row1.getCell(1);

            //excel表的第i行，第3列的单元格
            Cell cell4 = row1.getCell(2);
            //如果单元格中有数字或者其他格式的数据，则调用setCellType()转换为string类型
            Cell cell7 = row1.getCell(4);
            cell7.setCellType(CellType.type.getAnonymousUnionMemberOrdinal());//这里new 一个对象，用来装填从页面上传的Excel数据，字段根据上传的excel决定
            Excel excel = new Excel();

            excel.setName(cell3.getStringCellValue());
            excel.setAge(cell4.getStringCellValue());
            excel.setAddress(cell7.getStringCellValue());
            list.add(excel);
        }
        excelRepository.saveAll(list);//批量插入数据
    }


}
