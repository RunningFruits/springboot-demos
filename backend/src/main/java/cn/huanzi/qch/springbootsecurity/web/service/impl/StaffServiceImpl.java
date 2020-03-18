package cn.huanzi.qch.springbootsecurity.web.service.impl;


import cn.huanzi.qch.springbootsecurity.web.pojo.Staff;
import cn.huanzi.qch.springbootsecurity.web.repository.StaffDao;
import cn.huanzi.qch.springbootsecurity.web.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    StaffDao staffDao;

    @Override
    public Page<Staff> findByPage(Pageable pageable) {
        return staffDao.findAll(pageable);
    }

}
