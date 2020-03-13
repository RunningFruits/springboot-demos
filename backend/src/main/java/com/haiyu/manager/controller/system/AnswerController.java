package com.haiyu.manager.controller.system;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.haiyu.manager.dto.UserAnswerDTO;
import com.haiyu.manager.response.PageDataResult;
import com.haiyu.manager.service.UserAnswerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("answer")
public class AnswerController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserAnswerService userAnswerService;

    /**
     * 功能描述: 跳到家长回答记录
     */
    @RequestMapping("index")
    public String index() {
        logger.info("进入家长回答记录");
        return "answer/index";
    }


    /**
     * 功能描述: 获取家长回答记录列表
     */
    @PostMapping("getList")
    @ResponseBody
    public PageDataResult getList(@RequestParam("pageNum") Integer pageNum,
                                  @RequestParam("pageSize") Integer pageSize) {
        logger.info("获取家长回答记录列表");
        PageDataResult pdr = new PageDataResult();
        try {
            if (null == pageNum) {
                pageNum = 1;
            }
            if (null == pageSize) {
                pageSize = 10;
            }
            // 获取服务类目列表
            pdr = userAnswerService.getList(pageNum, pageSize);
            logger.info("家长回答记录列表查询=pdr:" + pdr);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("家长回答记录列表查询异常！", e);
        }
        return pdr;
    }

    /**
     * 功能描述: 删除答题记录，为防特殊情况重答
     */
    @RequestMapping(value = "/delById", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> update(@RequestParam("id") Integer id) {
        logger.info("删除答题记录！id:" + id);
        Map<String, Object> data = new HashMap<>();
        data = userAnswerService.delById(id);
        return data;
    }

    @RequestMapping("/exportExcel")
    @ResponseBody
    public Object exportExcel(HttpServletRequest request, Model model) {

        Map<String, Object> map = new HashMap<String, Object>();

        try {
            PageDataResult pdr = userAnswerService.getList(1, 99999);
            map.put("code", 0);
            map.put("msg", "导出成功");
            map.put("count", pdr.getTotals());
            map.put("data", pdr.getList());
        } catch (Exception e) {
            map.put("code", 1);
            map.put("msg", "导出失败，请稍后重试！");
        }
        return JSON.toJSON(map);
    }

}
