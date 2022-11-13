package com.blog.service.impl;

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
//        // 查询用户信息（查询数据库，这里使用假数据）
//        if (!username.equals("yang")) {
//            throw new RuntimeException("用户名错误！！！");
//        }
//        String password = "{noop}123456";
//        // TODO 查询对应的权限信息
//
//        // 把数据封装成LoginUser对象返回
//        return new LoginUser(username, password);
        return null;
    }

//    //查询用户信息
//    LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(User::getUserName,username);
//    User user = userMapper.selectOne(queryWrapper);
//    //如果没有查询到用户就抛出异常
//        if(Objects.isNull(user)){
//        throw new RuntimeException("用户名或者密码错误");
//    }
//
//    //        List<String> list = new ArrayList<>(Arrays.asList("test","admin"));
//    List<String> list = menuMapper.selectPermsByUserId(user.getId());
//    //把数据封装成UserDetails返回
//        return new LoginUser(user,list);
}
