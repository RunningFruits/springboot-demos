package com.code.demo.websocket.repository;

import com.code.demo.websocket.entity.MainControl;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Repository
public interface MainControlRepository extends PagingAndSortingRepository<MainControl, Long>, JpaSpecificationExecutor, CrudRepository<MainControl, Long> {


}
