package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.mapper.UserMapper;
import com.blog.pojo.User;
import com.blog.service.UserService;
import com.blog.utils.R;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author a1002
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private UserService userService;


    @Override
    public User findByUsername(String username) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        User user = userService.getOne(queryWrapper);
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户名或者密码错误");
        }
        return user;
    }

    @Override
    @Transactional
    public R updateByID(Long id, String username, String password, String img, String blogPath, String grade) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getId, id);
        userService.removeById(id);
        User user = new User(id, username, password, img, blogPath, grade);
        userService.save(user);
        return R.success("success");
    }

    @Override
    public R list(int page, int size, String name) {
        Page<User> pageInfo = new Page<>(page, size);
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name != null, User::getUsername, name);
        queryWrapper.orderByDesc(User::getUpdateTime);
        userService.page(pageInfo, queryWrapper);

        return R.success(pageInfo);
    }

    @Override
    public R listByGrade(int page, int size, String grade) {
        Page<User> pageInfo = new Page<>(page, size);
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(grade != null, User::getGrade, grade);
        queryWrapper.orderByDesc(User::getUpdateTime);
        userService.page(pageInfo, queryWrapper);
        return R.success(pageInfo);
    }
}
