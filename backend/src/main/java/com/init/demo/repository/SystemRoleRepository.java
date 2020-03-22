package com.init.demo.repository;

import com.init.demo.entity.system.SystemRole;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemRoleRepository extends PagingAndSortingRepository<SystemRole, Long>, JpaSpecificationExecutor {
    List<SystemRole> findAll();

    @Query(value = "select action_id from sys_role_action where role_id = ?1", nativeQuery = true)
    public Long[] getMenusByRoleId(Long roleId);

    @Query(value = "select ur.role_id from sys_user_role ur where ur.user_id = ?1", nativeQuery = true)
    public Long[] getUserRoles(String userId);


    /**
     * 根据用户Id 查询最高级角色
     *
     * @param userId
     * @return
     */
    @Query(value = "select * from system_role where id=(select min(role_id) role_id  from  sys_user_role where user_id= ?1)", nativeQuery = true)
    public SystemRole findByUserId(String userId);

    public void deleteById(Long id);

    /**
     * 查询角色 in(id)
     *
     * @param id
     * @return
     */
    List<SystemRole> findAllByIdIn(Long[] id);

    /**
     * 查询角色 notin(roleName)
     *
     * @param roleName
     * @return
     */
    List<SystemRole> findAllByRoleNameNotIn(String[] roleName);

    /**
     * 根据Id查询
     *
     * @param id
     * @return
     */
    public SystemRole findSystemRoleById(Long id);

    /**
     * 新增时检验角色名称是否存在
     *
     * @param roleName
     * @return
     */
    public int countSystemRoleByRoleName(String roleName);

    /**
     * 修改时检验角色名称是否存在
     *
     * @param roleName
     * @param id
     * @return
     */
    public int countSystemRoleByRoleNameAndIdNot(String roleName, long id);

    /**
     * 新增时检验角色显示名称是否存在
     *
     * @param displayName
     * @return
     */
    public int countSystemRoleByDisplayName(String displayName);

    /**
     * 修改时检验角色显示名称是否存在
     *
     * @param displayName
     * @param id
     * @return
     */
    public int countSystemRoleByDisplayNameAndIdNot(String displayName, long id);

}
