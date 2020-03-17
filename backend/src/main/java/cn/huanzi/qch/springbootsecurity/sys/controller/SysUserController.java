package cn.huanzi.qch.springbootsecurity.sys.controller;

import cn.huanzi.qch.springbootsecurity.common.controller.*;
import cn.huanzi.qch.springbootsecurity.common.pojo.PageInfo;
import cn.huanzi.qch.springbootsecurity.common.pojo.Result;
import cn.huanzi.qch.springbootsecurity.sys.pojo.SysUser;
import cn.huanzi.qch.springbootsecurity.sys.vo.SysUserVo;
import cn.huanzi.qch.springbootsecurity.sys.service.SysUserService;
import cn.huanzi.qch.springbootsecurity.util.FileWithExcelUtil;
import cn.huanzi.qch.springbootsecurity.web.pojo.UserAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/sys/user")
public class SysUserController extends CommonController<SysUserVo, SysUser, String> {
    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("")
    public String index(){
        return "sys/user/index";
    }


    @RequestMapping("/exportExcel")
    public Result export(HttpServletResponse response) {
        try {
            //模拟从数据库获取需要导出的数据
            List<UserAnswer> personList = new ArrayList<>();
            FileWithExcelUtil.exportExcel(personList, "客户信息表", "客户表", UserAnswer.class, "客户表.xls", response);
            return Result.ok("操作成功");
        } catch (Exception e) {
            return Result.error(500, "导出模版失败");
        }
    }


}
