package cn.huanzi.qch.springbootsecurity.sys.service.impl;

import cn.huanzi.qch.springbootsecurity.common.pojo.Result;
import cn.huanzi.qch.springbootsecurity.common.service.CommonServiceImpl;
import cn.huanzi.qch.springbootsecurity.sys.pojo.SysUser;
import cn.huanzi.qch.springbootsecurity.sys.repository.SysUserRepository;
import cn.huanzi.qch.springbootsecurity.sys.service.SysUserService;
import cn.huanzi.qch.springbootsecurity.sys.vo.SysUserVo;
import cn.huanzi.qch.springbootsecurity.util.CopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
@Transactional
public class SysUserServiceImpl extends CommonServiceImpl<SysUserVo, SysUser, String> implements SysUserService {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private SysUserRepository sysUserRepository;

    @Override
    public Result<SysUserVo> findByLoginName(String username) {
        return Result.ok(CopyUtil.copy(sysUserRepository.findByLoginName(username),SysUserVo.class));
    }
}
