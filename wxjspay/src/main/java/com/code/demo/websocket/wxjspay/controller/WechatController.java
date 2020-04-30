package com.code.demo.websocket.wxjspay.controller;


import com.alibaba.fastjson.JSONObject;
import com.code.demo.websocket.wxjspay.service.IWeChatService;
import com.code.demo.websocket.wxjspay.utils.WxOpenIdUtil;
import com.code.demo.backend.api.utils.StrKit;
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

    @Value("${wechat.domain.url}")
    private String domain;

    @Value("${wechat.domain.callbackUrl}")
    private String callbackDomain;

    @Value("${wechat.domain.oauth2Url}")
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
        view.setViewName("wxpage/list");
        return view;
    }

    @RequestMapping("/scene/detail")
    public ModelAndView sceneDetail(HttpServletRequest request) {
        ModelAndView view = new ModelAndView();
        view.addObject("domain", domain);
        view.setViewName("wxpage/detail");
        return view;
    }

    @RequestMapping("/scene/introduce")
    public ModelAndView sceneIntroduce(HttpServletRequest request) {
        ModelAndView view = new ModelAndView();
        view.addObject("domain", domain);
        view.setViewName("wxpage/introduce");
        return view;
    }

    //购买须知
    @RequestMapping("/scene/buyNotice")
    public ModelAndView sceneBuyNotice(HttpServletRequest request) {
        ModelAndView view = new ModelAndView();
        view.addObject("domain", domain);
        view.setViewName("wxpage/buyNotice");
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
        view.setViewName("wxpage/appoint");
        return view;
    }

    //购买协议
    @RequestMapping("/scene/buyAgreement")
    public ModelAndView buyAgreement(HttpServletRequest request) {
        ModelAndView view = new ModelAndView();
        view.addObject("domain", domain);
        view.setViewName("wxpage/buyAgreement");
        return view;
    }

}
