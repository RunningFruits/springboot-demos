package cn.huanzi.qch.springbootsecurity.sys.service.impl;

import cn.huanzi.qch.springbootsecurity.common.service.*;
import cn.huanzi.qch.springbootsecurity.sys.pojo.SysMenu;
import cn.huanzi.qch.springbootsecurity.sys.service.SysMenuService;
import cn.huanzi.qch.springbootsecurity.sys.vo.SysMenuVo;
import cn.huanzi.qch.springbootsecurity.sys.repository.SysMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
@Transactional
public class SysMenuServiceImpl extends CommonServiceImpl<SysMenuVo, SysMenu, String> implements SysMenuService {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private SysMenuRepository sysMenuRepository;
}
