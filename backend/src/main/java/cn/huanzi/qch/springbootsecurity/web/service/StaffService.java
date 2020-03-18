package cn.huanzi.qch.springbootsecurity.web.service;


import cn.huanzi.qch.springbootsecurity.web.pojo.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface StaffService {
    Page<Staff> findByPage(Pageable pageable);
}
