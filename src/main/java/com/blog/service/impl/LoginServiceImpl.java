package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.blog.mapper.UserMapper;
import com.blog.pojo.ResponseResult;
import com.blog.pojo.User;
import com.blog.service.LoginServcie;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author a1002
 */
@Service
public class LoginServiceImpl implements LoginServcie {

    @Resource
    private UserMapper userMapper;


    @Override
    public ResponseResult login(String username) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        User user = userMapper.selectOne(queryWrapper);
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户名或密码错误");
        }
        return null;
    }

    @Override
    public ResponseResult logout() {
        return null;
    }
}
