package com.shine.video.web;

import com.github.pagehelper.PageHelper;
import com.shine.video.bean.Constant;
import com.shine.video.bean.ResultBean;
import io.swagger.annotations.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Api(value = "collect", description = "收藏相关接口")
@RestController
@RequestMapping(value = "collect")
public class CollectController extends BaseController {


    @ApiOperation(value = "", httpMethod = "GET", notes = "收藏列表")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResultBean list(HttpServletRequest request,
                           @ApiParam(required = true, name = "pageNo", value = "第几页") @RequestParam(defaultValue = "1", value = "pageNo")
                                   Integer pageNo,
                           @ApiParam(required = true, name = "pageSize", value = "每页显示条数") @RequestParam(defaultValue = "10", value = "pageSize")
                                   Integer pageSize
    ) throws Exception {

        if (Constant.USER_TYPE_SPECIAL != (int) request.getAttribute("type") &&
                Constant.USER_TYPE_ORDINARY != (int) request.getAttribute("type")) {
            throw new HttpMessageNotReadableException("该用户没有查看收藏列表权限");
        }
        /*
         * 第一个参数是第几页；第二个参数是每页显示条数。
         */
        PageHelper.startPage(pageNo, pageSize);
        return ResultBean.success(collectService.page((Integer) request.getAttribute("userId")));
    }

    @ApiOperation(value = "/{id}", notes = "添加收藏", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "String", paramType = "query", name = "id", value = "收藏id", required = true),
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResultBean collect(
            HttpServletRequest request,
            @ApiParam(required = true, name = "id", value = "收藏id") @PathVariable Integer id
    ) throws Exception {

        if (Constant.USER_TYPE_SPECIAL != (int) request.getAttribute("type") &&
                Constant.USER_TYPE_ORDINARY != (int) request.getAttribute("type")) {
            throw new HttpMessageNotReadableException("该用户没有收藏权限");
        }
        collectService.collect((Integer) request.getAttribute("userId"), id);
        return ResultBean.SUCCESS;
    }

    @ApiOperation(value = "/{id}", notes = "删除收藏", httpMethod = "DELETE")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "String", paramType = "query", name = "id", value = "收藏id", required = true),
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResultBean delete(
            HttpServletRequest request,
            @ApiParam(required = true, name = "id", value = "收藏id") @PathVariable Integer id
    ) throws Exception {
        if (Constant.USER_TYPE_SPECIAL != (int) request.getAttribute("type") && Constant.USER_TYPE_ORDINARY != (int) request.getAttribute("type")) {
            throw new HttpMessageNotReadableException("该用户没有删除收藏权限");
        }
        collectService.delete((Integer) request.getAttribute("userId"), id);
        return ResultBean.SUCCESS;
    }

}
