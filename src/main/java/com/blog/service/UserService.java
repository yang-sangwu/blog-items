package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.pojo.User;

/**
 * @author a1002
 */
@SuppressWarnings("all")
public interface UserService extends IService<User> {
    User findByUsername(String username);
}
