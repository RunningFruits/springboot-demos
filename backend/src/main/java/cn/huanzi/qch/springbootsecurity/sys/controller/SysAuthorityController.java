package cn.huanzi.qch.springbootsecurity.sys.controller;

import cn.huanzi.qch.springbootsecurity.common.controller.*;
import cn.huanzi.qch.springbootsecurity.sys.pojo.SysAuthority;
import cn.huanzi.qch.springbootsecurity.sys.vo.SysAuthorityVo;
import cn.huanzi.qch.springbootsecurity.sys.service.SysAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sys/authority")
public class SysAuthorityController extends CommonController<SysAuthorityVo, SysAuthority, String> {

    @Autowired
    private SysAuthorityService sysAuthorityService;

    @RequestMapping("")
    public String index(){
        return "sys/authority/index";
    }

}
