package cn.huanzi.qch.springbootsecurity.sys.repository;

import cn.huanzi.qch.springbootsecurity.common.repository.CommonRepository;
import cn.huanzi.qch.springbootsecurity.sys.pojo.SysUserMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserMenuRepository extends CommonRepository<SysUserMenu, String> {
    List<SysUserMenu> findByUserId(String userId);
}
