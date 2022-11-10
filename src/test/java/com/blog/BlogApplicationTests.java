package com.blog;

import com.blog.pojo.User;
import com.blog.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class BlogApplicationTests {

    @Resource
    private UserService userService;

    @Test
    void contextLoads() {
    }

    @Test
    public void tetsSave() {
        User user = new User("yangsiaya", "123456", "m", "2021");
        userService.save(user);
    }
}
