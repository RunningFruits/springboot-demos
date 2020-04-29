package com.code.demo.backend.repository.system;

import com.code.demo.backend.entity.system.SystemLog;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface SystemLogRepository extends PagingAndSortingRepository<SystemLog, String>, JpaSpecificationExecutor {
}
