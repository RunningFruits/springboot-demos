package com.hosiang.dev.controller.echarts;


import com.hosiang.dev.service.impl.echarts.AreaStackImpl;
import com.huajie.base.ResponseBase;
import com.huajie.echarts.AbstractAreaStack;
import com.huajie.entity.AreaStack;
import com.huajie.utils.QueryUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author xwf
 */
@RestController
@RequestMapping("/report/echarts")
public class AreaController extends BaseController {


    /**
     * barStack 多对象柱状图
     */
    @PostMapping("/areaStack")
    @ResponseBody
    public ResponseBase barStack(HttpServletRequest request) {
        Map<String, Object> conditionMap = QueryUtils.getParams(request);
        AbstractAreaStack areaStack = echartsFactory.createAreaStack(conditionMap, AreaStackImpl.class);
        AreaStack areaStackData = areaStack.initEchartsData();
        return setResultSuccess(areaStackData);
    }

}
