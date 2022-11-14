package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.pojo.User;
import com.blog.utils.R;

/**
 * @author a1002
 */
@SuppressWarnings("all")
public interface UserService extends IService<User> {
    User findByUsername(String username);

    R updateByID(Long id, String username, String password, String img, String blogPath, String grade);
}
