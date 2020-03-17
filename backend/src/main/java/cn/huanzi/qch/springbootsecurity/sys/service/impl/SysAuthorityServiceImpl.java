package cn.huanzi.qch.springbootsecurity.sys.service.impl;

import cn.huanzi.qch.springbootsecurity.common.service.*;
import cn.huanzi.qch.springbootsecurity.sys.pojo.SysAuthority;
import cn.huanzi.qch.springbootsecurity.sys.service.SysAuthorityService;
import cn.huanzi.qch.springbootsecurity.sys.vo.SysAuthorityVo;
import cn.huanzi.qch.springbootsecurity.sys.repository.SysAuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
@Transactional
public class SysAuthorityServiceImpl extends CommonServiceImpl<SysAuthorityVo, SysAuthority, String> implements SysAuthorityService {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private SysAuthorityRepository sysAuthorityRepository;
}
