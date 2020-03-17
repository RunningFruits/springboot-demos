package cn.huanzi.qch.springbootsecurity.sys.service;

import cn.huanzi.qch.springbootsecurity.common.pojo.Result;
import cn.huanzi.qch.springbootsecurity.common.service.CommonService;
import cn.huanzi.qch.springbootsecurity.sys.pojo.SysUserMenu;
import cn.huanzi.qch.springbootsecurity.sys.vo.SysUserMenuVo;

import java.util.List;

public interface SysUserMenuService extends CommonService<SysUserMenuVo, SysUserMenu, String> {
    Result<List<SysUserMenuVo>> findByUserId(String userId);
}
