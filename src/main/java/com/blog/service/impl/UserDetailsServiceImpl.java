package com.blog.service.impl;

import com.blog.pojo.LoginUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 认证
 *
 * @Author zwp
 * @Date 2022/9/12 16:53
 **/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户信息（查询数据库，这里使用假数据）
        if (!username.equals("yang")) {
            throw new RuntimeException("用户名错误！！！");
        }
        String password = "{noop}123456";
        // TODO 查询对应的权限信息

        // 把数据封装成LoginUser对象返回
        return new LoginUser(username, password);
    }
}
