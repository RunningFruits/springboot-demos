package com.code.demo.backend.repository.system;

import com.code.demo.backend.entity.system.SysUserRole;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserRoleRepository extends PagingAndSortingRepository<SysUserRole, Long>, JpaSpecificationExecutor {
    //    @Modifying
//    @Query(value = "delete from sys_user_role where user_id = ?1", nativeQuery = true)
    public void deleteByUserId(String userId);

    public void deleteByRoleId(Long roleId);
}
