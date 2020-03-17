package cn.huanzi.qch.springbootsecurity.sys.repository;

import cn.huanzi.qch.springbootsecurity.common.repository.*;
import cn.huanzi.qch.springbootsecurity.sys.pojo.SysMenu;
import org.springframework.stereotype.Repository;

@Repository
public interface SysMenuRepository extends CommonRepository<SysMenu, String> {
}
