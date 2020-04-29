package com.code.demo.backend.repository.system;

import com.code.demo.backend.entity.system.SystemEnum;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemEnumRepository extends PagingAndSortingRepository<SystemEnum, String>, JpaSpecificationExecutor {

    public List<SystemEnum> findByRemarkLike(String remark);

    public List<SystemEnum> findByRemarkLikeAndValueLessThanEqual(String remark, Integer value);

    /**
     * 根据remark查询且value值不包含
     *
     * @param parentName
     * @param value
     * @return
     */
    public List<SystemEnum> findByRemarkLikeAndValueNotIn(String parentName, Integer[] value);

    public List<SystemEnum> findByRemarkLikeAndValueBetween(String remark, Integer little, Integer large);

    public List<SystemEnum> findAll();

    @Query(value = "select max(index_of) from system_enum se where se.parent_id = ?1", nativeQuery = true)
    public Integer findMaxIndexOf(Long parentId);

    public List<SystemEnum> findByParentId(Long parentId);

    public void deleteByParentId(Long parentId);

    public void deleteAllByIdIn(List<Long> ids);

    public void deleteById(Long id);


}
