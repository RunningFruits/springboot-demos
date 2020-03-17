package cn.huanzi.qch.springbootsecurity.sys.service;

import cn.huanzi.qch.springbootsecurity.common.pojo.Result;
import cn.huanzi.qch.springbootsecurity.common.service.CommonService;
import cn.huanzi.qch.springbootsecurity.sys.pojo.SysUser;
import cn.huanzi.qch.springbootsecurity.sys.vo.SysUserVo;

public interface SysUserService extends CommonService<SysUserVo, SysUser, String> {
    Result<SysUserVo> findByLoginName(String username);
}
