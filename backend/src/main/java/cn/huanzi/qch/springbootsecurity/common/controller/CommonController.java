package cn.huanzi.qch.springbootsecurity.common.controller;

import cn.huanzi.qch.springbootsecurity.common.pojo.PageInfo;
import cn.huanzi.qch.springbootsecurity.common.pojo.Result;
import cn.huanzi.qch.springbootsecurity.common.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 通用Controller
 *
 * @param <V> 实体类Vo
 * @param <E> 实体类
 * @param <T> id主键类型
 */
public class CommonController<V, E, T> {

    @Autowired
    private CommonService<V, E, T> commonService;

    /*
        CRUD、分页、排序测试
     */
    //方便测试暂时改成GetMapping
    @RequestMapping("page")
    @ResponseBody
    public Result<PageInfo<V>> page(V entityVo) {
        return commonService.page(entityVo);
    }

    //方便测试暂时改成GetMapping
    @RequestMapping("list")
    @ResponseBody
    public Result<List<V>> list(V entityVo) {
        return commonService.list(entityVo);
    }

    public Result<V> get(@PathVariable("id") T id) {
        return commonService.get(id);
    }

    @RequestMapping("save")
    @ResponseBody
    public Result<V> save(V entityVo) {
        return commonService.save(entityVo);
    }

    @RequestMapping("delete")
    @ResponseBody
    public Result<T> delete( @PathVariable("id") T id) {
        /*
        批量删除
        @DeleteMapping("deleteBatch")
        public Result<T> deleteBatch(@RequestBody List<String> ids){}
        前端调用：
        $.ajax({
            url: ctx + "deleteBatch",
            type: "DELETE",
            data: JSON.stringify([id1,id2]),
            dataType: "JSON",
            contentType: 'application/json',
            success: function (data) {
            }
        });
         */
        return commonService.delete(id);
    }
}
