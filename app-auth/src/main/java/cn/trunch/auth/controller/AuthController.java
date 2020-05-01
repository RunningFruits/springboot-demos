package cn.trunch.auth.controller;

import cn.trunch.auth.entity.Message;
import cn.trunch.auth.service.AuthService;
import cn.trunch.auth.util.QRCodeUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "auth",description = "授权接口")
@RestController
@RequestMapping(value = "auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @ApiOperation(value = "/token", notes = "客户端获取token", httpMethod = "POST")
    @PostMapping(value = "/token")
    @ResponseBody
    public Message getToken(HttpServletRequest request) {
        return authService.addAuthInfo(request);
    }

    @ApiOperation(value = "/img/{token}", notes = "token对应的二维码图像", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", value = "用户id", dataType = "String", example = "1"),
    })
    @PostMapping(value = "/img/{token}")
    public void getQRCodeImg(
            @ApiParam(name = "token", value = "用户token", required = true) @PathVariable("token") String token,
            HttpServletResponse response) {
        try {
            // 传入图像链接，还能够生成带logo的二维码
            QRCodeUtil.encode(token, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "/info/{token}", notes = "客户端和手机端获取token相关信息", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", value = "用户id", dataType = "String", example = "1"),
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户id", dataType = "String", example = "1"),
            @ApiImplicitParam(paramType = "query", name = "isScan", value = "是否扫码", dataType = "String", example = "1")
    })
    @PostMapping(value = "/info/{token}")
    @ResponseBody
    public Message infoToken(
            @ApiParam(name = "token", value = "用户token", required = true) @PathVariable("token") String token,
            @ApiParam(name = "userId", value = "用户id", required = true) String userId,
            @ApiParam(name = "isScan", value = "是否扫码", required = true) boolean isScan
    ) {
        return authService.getAuthInfo(token, userId, isScan);
    }

    @ApiOperation(value = "/use/{token}", notes = "手机端使用token进行验证", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", value = "用户id", dataType = "String", example = "1"),
            @ApiImplicitParam(paramType = "query", name = "userId", value = "用户id", dataType = "String", example = "1")
    })
    @PostMapping(value = "/use/{token}")
    @ResponseBody
    public Message useToken(
            @ApiParam(name = "token", value = "用户token", required = true) @PathVariable("token") String token,
            @ApiParam(name = "userId", value = "用户id", required = true) String userId
    ) {
        return authService.setAuthState(token, userId);
    }

}
