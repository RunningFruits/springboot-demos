package com.shine.video.web;

import com.github.pagehelper.PageHelper;
import com.shine.video.bean.Constant;
import com.shine.video.bean.ResultBean;
import com.shine.video.dao.model.User;
import com.shine.video.util.EncryptUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Api(value = "user", description = "用户相关接口")
@RestController
@RequestMapping(value = "user")
public class UserController extends BaseController {

    /**
     * 注册
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ApiOperation(value = "register", httpMethod = "POST", notes = "注册接口")
    public ResultBean register(
            HttpServletRequest request, HttpServletResponse response,
            @ApiParam(required = true, name = "username", value = "用户名") @RequestParam String username,
            @ApiParam(required = true, name = "password", value = "密码") @RequestParam String password
    ) {
//        if (Constant.USER_TYPE_SPECIAL != (int) request.getAttribute("type")) {
//            throw new HttpMessageNotReadableException("该用户没有注册权限");
//        }
        loginService.doRegister(username, password, request);
        return ResultBean.SUCCESS;
    }

    /**
     * 登录
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ApiOperation(value = "login", httpMethod = "POST", notes = "登录接口")
    public ResultBean login(
            HttpServletRequest request, HttpServletResponse response,
            @ApiParam(required = true, name = "username", value = "用户名") @RequestParam String username,
            @ApiParam(required = true, name = "password", value = "密码") @RequestParam String password
    ) throws Exception {
        User user = loginService.doLogin(username, password, request);
        String token = EncryptUtil.aesEncrypt(username, EncryptUtil.KEY);
        redisUtil.set(username, token);
        response.setHeader("Authorization", token);
        user.setToken(token);
        return ResultBean.success(user);
    }

    /**
     * 普通用户列表
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ApiOperation(value = "list", httpMethod = "GET", notes = "普通用户列表")
    public ResultBean list(
            HttpServletRequest request,
            @ApiParam(required = true, name = "pageNo", value = "第几页")
            @RequestParam(defaultValue = "1", value = "pageNo")
                    Integer pageNo,
            @ApiParam(required = true, name = "pageSize", value = "每页显示条数")
            @RequestParam(defaultValue = "10", value = "pageSize")
                    Integer pageSize
    ) throws Exception {
        if (Constant.USER_TYPE_SPECIAL != (int) request.getAttribute("type")) {
            throw new HttpMessageNotReadableException("该用户没有查看收藏列表权限");
        }
        PageHelper.startPage(pageNo, pageSize);
        return ResultBean.success(userService.page((Integer) request.getAttribute("userId")));
    }

    /**
     * 删除用户
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "/delete/{id}", httpMethod = "DELETE", notes = "删除用户")
    public ResultBean delete(
            HttpServletRequest request,
            @ApiParam(required = true, name = "id", value = "收藏id") @PathVariable Integer id
    ) throws Exception {
        if (Constant.USER_TYPE_SPECIAL != (int) request.getAttribute("type")) {
            throw new HttpMessageNotReadableException("该用户没有删除收藏权限");
        }
        userService.delete(id);
        return ResultBean.SUCCESS;
    }


}
