package com.tao.dao;

import com.tao.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author huangt
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);
}
