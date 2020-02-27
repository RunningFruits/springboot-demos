package com.hosiang.dev.controller.echarts;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author xwf
 */
@Controller
@RequestMapping
public class IndexController{

    @GetMapping(value = "/")
    public String index() {
        return "echarts/index";
    }

    @GetMapping(value = "/pie")
    public String pie() {
        return "echarts/pie";
    }

    @GetMapping(value = "/bar")
    public String bar() {
        return "echarts/bar";
    }

    @GetMapping(value = "/bar-y-category")
    public String barYCategory() {
        return "echarts/bar-y-category";
    }

    @GetMapping(value = "/line")
    public String line() {
        return "echarts/line";
    }

    @GetMapping(value = "/pie-doughnut")
    public String pieDoughnut() {
        return "echarts/pie-doughnut";
    }

    @GetMapping(value = "/area")
    public String area() {
        return "echarts/area";
    }

}
