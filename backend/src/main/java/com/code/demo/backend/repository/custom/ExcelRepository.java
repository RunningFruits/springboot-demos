package com.code.demo.backend.repository.custom;


import com.code.demo.backend.entity.custom.Excel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface ExcelRepository extends PagingAndSortingRepository<Excel, Long>, JpaSpecificationExecutor {

}
