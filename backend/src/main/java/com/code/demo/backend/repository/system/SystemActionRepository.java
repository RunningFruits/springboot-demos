package com.code.demo.backend.repository.system;

import com.code.demo.backend.entity.system.SystemAction;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface SystemActionRepository extends PagingAndSortingRepository<SystemAction, Long>, JpaSpecificationExecutor {

    @Query(value = "select distinct sa.* from system_action sa,system_user su,sys_user_role sur,sys_role_action sra where su.id = sur.user_id and sur.role_id = sra.role_id and sra.action_id = sa.id and sa.parent_id = 1 and su.id = ?1 order by sa.index_of", nativeQuery = true)
    public List<SystemAction> findTopMenuByLoginId(String loginId);

    @Query(value = "select distinct sa.* from system_action sa,system_user su,sys_user_role sur,sys_role_action sra where su.id = sur.user_id and sur.role_id = sra.role_id and sra.action_id = sa.id and sa.parent_id > 1 and su.id = ?1", nativeQuery = true)
    public List<SystemAction> findMenuByLoginId(String loginId);

    // 除了button外的所有菜单
    @Query(value = "select distinct sa.* from system_action sa,system_user su,sys_user_role sur,sys_role_action sra where su.id = sur.user_id and sur.role_id = sra.role_id and sra.action_id = sa.id and sa.button = 0 and su.id = ?1 order by sa.parent_id,sa.index_of", nativeQuery = true)
    public List<SystemAction> findAllMenuByLoginId(String loginId);

    // 除了button外的所有菜单
    @Query(value = "select distinct sa.* from system_action sa,system_user su,sys_user_role sur,sys_role_action sra where su.id = sur.user_id and sur.role_id = sra.role_id and sra.action_id = sa.id and sa.button = 0 and su.id = ?1 order by sa.index_of", nativeQuery = true)
    public List<SystemAction> findAllMenuByLoginIdOrderByIndexOf(String loginId);

    @Query(value = "select distinct sa.* from system_action sa,system_user su,sys_user_role sur,sys_role_action sra where su.id = sur.user_id and sur.role_id = sra.role_id and sra.action_id = sa.id and sa.button = 1 and su.id = ?1", nativeQuery = true)
    public List<SystemAction> findButtonByLoginId(String loginId);

    public SystemAction findTopByDisplayName(String displayName);

    public SystemAction findTopByDisplayNameAndParentIdNot(String displayName, Long parentId);


    /**
     * 菜单树 排序父结点，index_of
     *
     * @return
     */
    @Query(value = "select * from system_action sa order by parent_id,index_of", nativeQuery = true)
    public List<SystemAction> findAll();

    /**
     * 菜单列表 排序父结点，index_of
     *
     * @param parentId
     * @return
     */
    @Query(value = "select * from system_action sa where sa.parent_id = ?1 order by parent_id,index_of", nativeQuery = true)
    public List<SystemAction> findAllByParentIdOrderByIndexOf(Long parentId);

    @Query(value = "select max(index_of) from system_action sa where sa.index_of < 99 and sa.parent_id = ?1", nativeQuery = true)
    public Integer findMaxIndexOf(Long parentId);

    //@Modifying
    //@Query(value = "delete from system_action where parent_id = ?1", nativeQuery = true)
    public void deleteByParentId(Long parentId);

    //@Modifying
    //@Query(value = "delete from system_action where id = ?1", nativeQuery = true)
    public void deleteById(Long id);

    public void deleteAllByIdIn(List<Long> ids);

    @Modifying
    @Query(value = "UPDATE  system_action AS per  JOIN system_action AS b ON (per.`id` = ?1 AND b.`id` = ?2) SET per.`index_of`  = b.`index_of`, b.`index_of` = per.`index_of`", nativeQuery = true)
    public void swapSort(Long currentId, Long swapId);

    public int countByName(String name);

    public int countByNameAndIdNot(String name, Long id);

}
