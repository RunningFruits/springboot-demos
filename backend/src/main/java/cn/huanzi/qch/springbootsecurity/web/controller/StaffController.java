package cn.huanzi.qch.springbootsecurity.web.controller;

import cn.huanzi.qch.springbootsecurity.common.pojo.Result;
import cn.huanzi.qch.springbootsecurity.web.pojo.Staff;
import cn.huanzi.qch.springbootsecurity.web.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("staff")
public class StaffController {

    @Autowired
    public StaffService staffservice;

    @RequestMapping("/")
    public String index(HttpServletRequest request) {
        return "staff/staff";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Result list(HttpServletRequest request) {
        int page = Integer.parseInt(request.getParameter("page"));

        PageRequest pageRequest = PageRequest.of(page, 5);
        Page<Staff> staffPage = staffservice.findByPage(pageRequest);
        for (int i = 0; i < staffPage.getContent().size(); i++) {
            System.out.println(staffPage.getContent().get(i));
            System.out.println(staffPage.getTotalElements());
        }
        if (staffPage.getContent() != null) {
            return Result.ok(staffPage.getContent());
        } else {
            return Result.error(500, "内容为空！");
        }
    }

}
