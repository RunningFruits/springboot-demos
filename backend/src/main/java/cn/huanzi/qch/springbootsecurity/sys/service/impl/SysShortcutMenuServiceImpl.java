package cn.huanzi.qch.springbootsecurity.sys.service.impl;

import cn.huanzi.qch.springbootsecurity.common.pojo.Result;
import cn.huanzi.qch.springbootsecurity.common.service.CommonServiceImpl;
import cn.huanzi.qch.springbootsecurity.sys.pojo.SysShortcutMenu;
import cn.huanzi.qch.springbootsecurity.sys.repository.SysShortcutMenuRepository;
import cn.huanzi.qch.springbootsecurity.sys.service.SysShortcutMenuService;
import cn.huanzi.qch.springbootsecurity.sys.vo.SysShortcutMenuVo;
import cn.huanzi.qch.springbootsecurity.util.CopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
public class SysShortcutMenuServiceImpl extends CommonServiceImpl<SysShortcutMenuVo, SysShortcutMenu, String> implements SysShortcutMenuService {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private SysShortcutMenuRepository sysShortcutMenuRepository;

    @Override
    public Result<List<SysShortcutMenuVo>> findByUserId(String userId) {
        return Result.ok(CopyUtil.copyList(sysShortcutMenuRepository.findByUserId(userId), SysShortcutMenuVo.class));
    }
}
