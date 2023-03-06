package com.tao;

import com.tao.dao.UserRepository;
import com.tao.po.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional(rollbackFor = Exception.class)
    public void contextLoads() {
        User user = new User();
        user.setUsername("123");
        user.setNickname("123");
        user.setPassword("123");
//        User username = userRepository.findByUsername("123");
//        userRepository.delete(username);
//        System.out.println(username);
        userRepository.save(user);
    }

}
