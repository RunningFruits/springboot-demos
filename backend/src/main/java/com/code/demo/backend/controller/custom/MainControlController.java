package com.code.demo.backend.controller.custom;

import com.code.demo.backend.service.custom.MainControlService;
import com.code.demo.backend.controller.BaseController;
import com.code.demo.backend.utils.PageRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@Controller
@RequestMapping("mainControl")
public class MainControlController extends BaseController {

    @Autowired
    private PageRequestUtil pageRequestUtil;

    @Autowired
    MainControlService service;

    @RequestMapping("/")
    public String page() {
        return "custom/mainControl/index";
    }

    @ResponseBody
    @PostMapping(value = "/findAll")
    public Object findAll(@RequestParam Map<String, Object> params) {
        int page = Integer.parseInt(params.get("page").toString());
        int limit = Integer.parseInt(params.get("limit").toString());
        Pageable pageable = new PageRequest(page-1, limit);

        return service.findAll(pageable);
    }


}
