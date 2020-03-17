package cn.huanzi.qch.springbootsecurity.sys.repository;

import cn.huanzi.qch.springbootsecurity.common.repository.CommonRepository;
import cn.huanzi.qch.springbootsecurity.sys.pojo.SysShortcutMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysShortcutMenuRepository extends CommonRepository<SysShortcutMenu, String> {
    List<SysShortcutMenu> findByUserId(String userId);
}
