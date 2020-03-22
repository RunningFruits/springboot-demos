package com.init.demo.repository;

import com.init.demo.entity.system.SystemLog;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface SystemLogRepository extends PagingAndSortingRepository<SystemLog, String>, JpaSpecificationExecutor {
}
