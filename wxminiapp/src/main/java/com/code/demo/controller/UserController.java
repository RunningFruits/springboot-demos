package com.code.demo.controller;


import com.code.demo.bean.UserInfo;
import com.code.demo.dao.UserInfoDao;
import com.code.demo.model.JsonResult;
import com.code.demo.utils.EmojiUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
@Api(value = "用户注册接口")
public class UserController {

    @Autowired
    private UserInfoDao userInfoDao;

    @RequestMapping("/post")
    @ApiOperation(value = "用户提交 答题结果、分数", notes = "用户提交 答题结果、分数")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "query", name = "openid", value = "openid", required = true, dataType = "String"),
        @ApiImplicitParam(paramType = "query", name = "parentName", value = "家长名称", required = true, dataType = "String"),
        @ApiImplicitParam(paramType = "query", name = "parentPhone", value = "家长手机", required = true, dataType = "String"),
        @ApiImplicitParam(paramType = "query", name = "childName", value = "孩子名称", required = true, dataType = "String")
    })
    public JsonResult postAnswer(HttpServletRequest request) {
        String openid = request.getParameter("openid");
        String parentName = request.getParameter("parentName");
        String parentPhone = request.getParameter("parentPhone");
        String childName = request.getParameter("childName");

        parentName = EmojiUtil.emojiConverterToAlias(parentName);

        UserInfo byOpenid = userInfoDao.findByOpenid(openid);
        if (byOpenid != null) {//家长重新填写名称、手机、孩子名称

            byOpenid.setParentName(parentName);
            byOpenid.setParentPhone(parentPhone);
            byOpenid.setChildName(childName);

            userInfoDao.save(byOpenid);
            JsonResult ret = new JsonResult(200, null);
            return ret;
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setOpenid(openid);
        userInfo.setParentName(parentName);
        userInfo.setParentPhone(parentPhone);
        userInfo.setChildName(childName);
        userInfoDao.save(userInfo);

        JsonResult ret = new JsonResult(200, null);
        return ret;
    }


}
