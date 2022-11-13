package com.blog.service;


import com.blog.pojo.ResponseResult;

public interface LoginServcie {
    ResponseResult login(String usernaem);

    ResponseResult logout();

}
