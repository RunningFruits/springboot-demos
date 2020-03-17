package cn.huanzi.qch.springbootsecurity.sys.service.impl;

import cn.huanzi.qch.springbootsecurity.common.pojo.Result;
import cn.huanzi.qch.springbootsecurity.common.service.*;
import cn.huanzi.qch.springbootsecurity.sys.pojo.SysUserAuthority;
import cn.huanzi.qch.springbootsecurity.sys.service.SysUserAuthorityService;
import cn.huanzi.qch.springbootsecurity.sys.vo.SysUserAuthorityVo;
import cn.huanzi.qch.springbootsecurity.sys.repository.SysUserAuthorityRepository;
import cn.huanzi.qch.springbootsecurity.util.CopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
public class SysUserAuthorityServiceImpl extends CommonServiceImpl<SysUserAuthorityVo, SysUserAuthority, String> implements SysUserAuthorityService {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private SysUserAuthorityRepository sysUserAuthorityRepository;

    @Override
    public Result<List<SysUserAuthorityVo>> findByUserId(String userId) {
        return Result.ok(CopyUtil.copyList(sysUserAuthorityRepository.findByUserId(userId),SysUserAuthorityVo.class));
    }
}
