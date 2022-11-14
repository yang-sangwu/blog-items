package com.blog.service.impl;

/**
 * @author Li
 * @Date 2022/7/23 1:09
 */

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.blog.pojo.JwtUser;
import com.blog.pojo.User;
import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * JwtUserDetailsService
 * 实现UserDetailsService,重写loadUserByUsername方法
 * 返回随机生成的user,pass是密码,这里固定生成的
 * 如果你自己需要定制查询user的方法,请改造这里
 *
 * @author zhengkai.blog.csdn.net
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        User user = userService.getOne(queryWrapper);
        if (user != null) {
            return new JwtUser(Math.toIntExact(user.getId()), username, user.getPassword(), String.valueOf(user.getStatus()), true);
        } else {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        }
    }

}


