package com.code.demo.api.repository;

import com.code.demo.api.dataobject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsernameAndPasswordAndRole(String username, String password, Integer role);

    User findByUsernameAndRole(String username, Integer role);
}
