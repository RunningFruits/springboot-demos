package com.code.demo.controller;

import com.code.demo.bean.UserAnswer;
import com.code.demo.dao.UserAnswerDao;
import com.code.demo.model.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/answer")
@Api(value = "用户答题接口")
public class AnswerController {

    @Autowired
    private UserAnswerDao userAnswerDao;

    @PostMapping("/post")
    @ApiOperation(value = "用户提交 答题结果、分数", notes = "用户提交 答题结果、分数")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "query", name = "openid", value = "openid", required = true, dataType = "String"),
        @ApiImplicitParam(paramType = "query", name = "parentName", value = "家长名称", required = true, dataType = "String"),
        @ApiImplicitParam(paramType = "query", name = "score", value = "分数", required = true, dataType = "Integer")
    })
    public JsonResult postAnswer(HttpServletRequest request) {
        String openid = request.getParameter("openid");
        String parentName = request.getParameter("parentName");
        String score = request.getParameter("score");

        UserAnswer byOpenid = userAnswerDao.findByOpenid(openid);
        if(byOpenid != null){
            Map<String,Object> ret = new HashMap<>();
            ret.put("msg","不能重复答题");
            JsonResult result = new JsonResult(501, ret);
            return result;
        }

        UserAnswer userAnswer = new UserAnswer();
        userAnswer.setOpenid(openid);
        userAnswer.setParentName(parentName);
        userAnswer.setScore(score);

        userAnswerDao.save(userAnswer);

        JsonResult result = new JsonResult(200, null);
        return result;
    }


}
