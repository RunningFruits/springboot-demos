package com.hosiang.dev.controller;


import com.hosiang.dev.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("highcharts")
public class HighchartsController {

    @Autowired
    ChartService chartService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView findUserRegister(ModelAndView model) throws Exception {
        List<Map<String, Object>> userRegisterMonthly = chartService.getUserRegisterMonthly();

        model.addObject("users", userRegisterMonthly);
        model.setViewName("charts/user");
        return model;
    }

}
