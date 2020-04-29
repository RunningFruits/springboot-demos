package com.code.demo.backend.repository.system;

import com.code.demo.backend.entity.system.SysRoleAction;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface SysRoleActionRepository extends PagingAndSortingRepository<SysRoleAction, Long>, JpaSpecificationExecutor {

    //    @Modifying //这里是关键
//    @Query(value = "delete from sys_role_action where role_id = ?1", nativeQuery = true)
    public void deleteByRoleId(Long roleId);

    @Modifying
    @Query(value = "delete from sys_role_action where action_id not in (select id from system_action)", nativeQuery = true)
    public void deleteByActionId();
}
