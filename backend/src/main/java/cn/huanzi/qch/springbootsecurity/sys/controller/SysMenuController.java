package cn.huanzi.qch.springbootsecurity.sys.controller;

import cn.huanzi.qch.springbootsecurity.common.controller.*;
import cn.huanzi.qch.springbootsecurity.sys.pojo.SysMenu;
import cn.huanzi.qch.springbootsecurity.sys.vo.SysMenuVo;
import cn.huanzi.qch.springbootsecurity.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sys/menu")
public class SysMenuController extends CommonController<SysMenuVo, SysMenu, String> {
    @Autowired
    private SysMenuService sysMenuService;

    @RequestMapping("")
    public String index(){
        return "sys/menu/index";
    }

}
