package com.shine.video.web;

import com.github.pagehelper.PageHelper;
import com.shine.video.bean.Constant;
import com.shine.video.bean.ResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Api(value = "video", description = "视频相关接口")
@RestController
@RequestMapping("/video")
public class VideoController extends BaseController {


    /**
     * 视频列表（根据name模糊搜索）
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "", httpMethod = "GET", notes = "视频列表")
    public ResultBean list(
            HttpServletRequest request,
            @ApiParam(required = true, name = "pageNo", value = "第几页")
            @RequestParam(defaultValue = "1", value = "pageNo") Integer pageNo,
            @ApiParam(required = true, name = "pageSize", value = "每页显示条数")
            @RequestParam(defaultValue = "10", value = "pageSize") Integer pageSize,
            @ApiParam(name = "name", value = "视频名称") String name
    ) throws Exception {
        PageHelper.startPage(pageNo, pageSize);
        return ResultBean.success(videoService.page(name));
    }

    /**
     * 视频上传
     */
    @ApiOperation(value = "upload", httpMethod = "POST", notes = "视频上传")
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public ResultBean upload(HttpServletRequest request,
                             @ApiParam(name = "file", value = "视频 MultipartFile") @RequestParam("file") MultipartFile file,
                             @ApiParam(name = "name", value = "视频名称") String name) throws Exception {

        if (Constant.USER_TYPE_SPECIAL != (int) request.getAttribute("type")) {
            throw new HttpMessageNotReadableException("该用户没有上传视频权限");
        }

        videoService.upload((Integer) request.getAttribute("userId"), file, name);
        return ResultBean.SUCCESS;
    }

    /**
     * 视频上传带转码 测试
     */
    @ApiOperation(value = "/transCode/{id}", httpMethod = "POST", notes = "视频转码上传")
    @RequestMapping(value = "/transCode/{id}", method = RequestMethod.POST)
    public ResultBean transCode(HttpServletRequest request,
                                @ApiParam(name = "file", value = "视频 MultipartFile", required = true) @RequestParam("file")
                                        MultipartFile file,
                                @ApiParam(name = "id", value = "视频 id") @PathVariable Integer id) throws Exception {
        videoService.transCode(id, file);
        return ResultBean.SUCCESS;
    }

    /**
     * 视频展示接口
     */
    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "/show/{id}", httpMethod = "GET", notes = "视频列表")
    public ResultBean One(HttpServletRequest request, @PathVariable Integer id) throws Exception {
        return ResultBean.success(videoService.selectOne(id));
    }

    /**
     * 删除视频
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "/{id}", httpMethod = "DELETE", notes = "删除视频")
    public ResultBean delete(HttpServletRequest request, @PathVariable Integer id) throws Exception {
        if (Constant.USER_TYPE_SPECIAL != (int) request.getAttribute("type")) {
            throw new HttpMessageNotReadableException("该用户没有删除视频权限");
        }
        videoService.delete(id);
        return ResultBean.SUCCESS;
    }

}
