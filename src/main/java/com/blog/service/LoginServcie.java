package com.blog.service;


import com.blog.pojo.JwtUser;
import com.blog.utils.R;

import javax.servlet.http.HttpServletRequest;

/**
 * @author a1002
 */
public interface LoginServcie {
    JwtUser isLogin(HttpServletRequest request);

    R login(String admin, String password);

    R register(String admin, String password);

    //退出登录
    void quit(HttpServletRequest request);

}
