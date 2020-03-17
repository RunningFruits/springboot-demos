package cn.huanzi.qch.springbootsecurity.sys.service;

import cn.huanzi.qch.springbootsecurity.common.pojo.Result;
import cn.huanzi.qch.springbootsecurity.common.service.CommonService;
import cn.huanzi.qch.springbootsecurity.sys.pojo.SysShortcutMenu;
import cn.huanzi.qch.springbootsecurity.sys.vo.SysShortcutMenuVo;

import java.util.List;

public interface SysShortcutMenuService extends CommonService<SysShortcutMenuVo, SysShortcutMenu, String> {
    Result<List<SysShortcutMenuVo>> findByUserId(String userId);
}
