package com.init.demo.controller.system;

import com.init.demo.annotation.OperationLog;
import com.init.demo.controller.BaseController;
import com.init.demo.entity.system.SystemEnum;
import com.init.demo.repository.system.SystemEnumRepository;
import com.init.demo.service.system.SystemEnumService;
import com.init.demo.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/systemEnum")
public class SystemEnumController extends BaseController {

    @Autowired
    private SystemEnumService service;

    @Autowired
    private SystemEnumRepository repository;

    @GetMapping(value = "")
    public String systemAction() {
        return "system/enumManager";
    }

    @ResponseBody
    @GetMapping(value = "/findByRemark/{remark}")
    public List<SystemEnum> findByRemark(@PathVariable("remark") String remark) {
        return repository.findByRemarkLike(remark + "::%");
    }

    // 通过remark查找小于等于le的枚举
    @ResponseBody
    @GetMapping(value = "/findByRemarkAndValueLE/{remark}/{le}")
    public List<SystemEnum> findByRemarkAndValueLE(@PathVariable("remark") String remark, @PathVariable("le") Integer le) {
        return repository.findByRemarkLikeAndValueLessThanEqual(remark + "::%", le);
    }

    /**
     * 根据remark查询且value值不包含 比如密级不包含内部remark值为：secret@0，以@为分隔符
     *
     * @param remark
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/findByRemarkValueNotin/{remark}")
    public List<SystemEnum> findByRemarkLikeAndValueNotIn(@PathVariable("remark") String remark) {
        String str[] = remark.split("@");
        String like = str[0] + "::%";
        Integer[] value = getValue(remark);
        if (StringUtils.isEmpty(value)) {
            return repository.findByRemarkLike(like);
        }
        return repository.findByRemarkLikeAndValueNotIn(like, value);
    }

    @ResponseBody
    @GetMapping(value = "/findAllToDTree")
    public ResponseResult findAllToDTree() {
        return ResponseResult.success(service.changeToDTree(0l));
    }

    @OperationLog("编辑/添加枚举")
    @ResponseBody
    @PostMapping(value = "/save")
    public ResponseResult save(@RequestBody SystemEnum SystemEnum) {
        return service.save(SystemEnum);
    }

    @ResponseBody
    @GetMapping("/listByParent")
    public ResponseResult listByParent(@RequestParam(required = false) Long parentId) {
        List<SystemEnum> menuList = repository.findByParentId(parentId);
        return ResponseResult.success(menuList);
    }

    @OperationLog("删除枚举")
    @ResponseBody
    @GetMapping(value = "/deleteEnum/{id}")
    public ResponseResult deleteEnum(@PathVariable("id") Long id) {
        return service.deleteEnum(id);
    }

    public Integer[] getValue(String remark) {
        if (StringUtils.isEmpty(remark)) {
            return null;
        } else {
            String str[] = remark.split("@");
            if (str.length > 1) {
                int count = 0;
                Integer[] value = new Integer[str.length - 1];
                for (int i = 1; i < str.length; i++) {
                    value[count] = Integer.valueOf(str[i]);
                    count++;
                }
                return value;
            } else {
                return null;
            }
        }
    }
}
