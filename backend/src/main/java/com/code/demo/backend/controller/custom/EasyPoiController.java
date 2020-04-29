package com.code.demo.backend.controller.custom;


import com.code.demo.backend.utils.DateUtil;
import com.code.demo.backend.entity.custom.Person;
import com.code.demo.backend.utils.easypoi.EasyPoiExcelUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/easyPoi")
public class EasyPoiController {

    @RequestMapping("/export")
    public void export(HttpServletResponse response) {
        //模拟从数据库获取需要导出的数据
        List<Person> personList = new ArrayList<>();
        Person person1 = new Person("路飞", "1", new Date());
        Person person2 = new Person("娜美", "2", DateUtil.addDate(new Date(), 3));
        Person person3 = new Person("索隆", "1", DateUtil.addDate(new Date(), 10));
        Person person4 = new Person("小狸猫", "1", DateUtil.addDate(new Date(), -10));
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        personList.add(person4);

        //导出操作
        EasyPoiExcelUtils.exportExcel(personList, "花名册", "草帽一伙", Person.class, "海贼王.xls", response);
    }

    @RequestMapping(value = "/")
    public String uploadPage() {
        return "custom/easypoi/upload";
    }

    /**
     * 导入
     *
     * @param file
     */
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    @ResponseBody
    public Object importExcel(@RequestParam("file") MultipartFile file) throws IOException {
        List<Person> personList = EasyPoiExcelUtils.importExcel(file, 1, 1, Person.class);

        return personList;
    }

}
