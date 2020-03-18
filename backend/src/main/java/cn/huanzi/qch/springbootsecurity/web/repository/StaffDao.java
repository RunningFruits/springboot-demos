package cn.huanzi.qch.springbootsecurity.web.repository;

import cn.huanzi.qch.springbootsecurity.web.pojo.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffDao extends JpaRepository<Staff,Integer> {

}
