package com.code.weixinjspay.controller;


import com.alibaba.fastjson.JSONObject;
import com.code.weixinjspay.service.IWeChatService;
import com.code.weixinjspay.utils.WxOpenIdUtil;
import com.demo.utils.StrKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@Controller
@RequestMapping("/wechat")
public class WechatController {

    @Autowired
    IWeChatService wechatService;

    @Value("${domain.url}")
    private String domain;

    @Value("${domain.callbackUrl}")
    private String callbackDomain;

    @Value("${domain.oauth2Url}")
    private String oauth2Url;

    @PostMapping("/scene/list")
    public ModelAndView sceneList(HttpServletRequest request) {
        ModelAndView view = new ModelAndView();

        //微信扫一扫进入
        String code = request.getParameter("code");
        if (!StrKit.isBlank(code)) {
            JSONObject wxUser = null;
            try {
                wxUser = WxOpenIdUtil.getOpenid(code);
                String openid = wxUser.getString("openid");

                System.out.println("========= openid =========");
                System.out.println(openid);
            } catch (IOException e) {
                System.out.println("==== 报错 =======");
            }
        }

        view.addObject("domain", domain);
        view.setViewName("wechat/scene/list");
        return view;
    }

    @RequestMapping("/scene/detail")
    public ModelAndView sceneDetail(HttpServletRequest request) {
        ModelAndView view = new ModelAndView();
        view.addObject("domain", domain);
        view.setViewName("wechat/scene/detail");
        return view;
    }

    @RequestMapping("/scene/introduce")
    public ModelAndView sceneIntroduce(HttpServletRequest request) {
        ModelAndView view = new ModelAndView();
        view.addObject("domain", domain);
        view.setViewName("wechat/scene/introduce");
        return view;
    }

    //购买须知
    @RequestMapping("/scene/buyNotice")
    public ModelAndView sceneBuyNotice(HttpServletRequest request) {
        ModelAndView view = new ModelAndView();
        view.addObject("domain", domain);
        view.setViewName("wechat/scene/buyNotice");
        return view;
    }

    /**
     * 需要三个参数：
     * （1）userId；
     * （2）companyId；
     * （3）ticketId；
     *
     * @param request
     * @return
     */
    @RequestMapping("/scene/appoint")
    public ModelAndView sceneAppoint(HttpServletRequest request) {
        ModelAndView view = new ModelAndView();
        view.addObject("domain", domain);
        view.setViewName("wechat/scene/appoint");
        return view;
    }

    //购买协议
    @RequestMapping("/scene/buyAgreement")
    public ModelAndView buyAgreement(HttpServletRequest request) {
        ModelAndView view = new ModelAndView();
        view.addObject("domain", domain);
        view.setViewName("wechat/scene/buyAgreement");
        return view;
    }

}
