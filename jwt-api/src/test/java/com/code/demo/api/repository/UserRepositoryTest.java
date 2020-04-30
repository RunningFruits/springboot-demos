package com.code.demo.api.repository;

import com.code.demo.api.JwtApiBootTests;
import com.code.demo.api.dataobject.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserRepositoryTest extends JwtApiBootTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindAll() {
        List<User> userList = userRepository.findAll();
        System.out.println(userList);
        Assert.assertNotNull(userList);

    }

}
