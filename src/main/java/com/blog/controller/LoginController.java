package com.blog.controller;

import com.blog.pojo.JwtUser;
import com.blog.service.LoginServcie;
import com.blog.service.UserService;
import com.blog.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author a1002
 */
@Api(tags = "login")
@Slf4j
@RestController
//@RequestMapping("/login")
public class LoginController {
    @Resource
    private UserService userService;

    @Resource
    private LoginServcie loginServcie;

    /**
     * 账号注册
     *
     * @param admin
     * @param password
     * @return
     */
    @ApiOperation(value = "账号注册")
    @PostMapping(value = "/register")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "admin", value = "账号", required = true),
            @ApiImplicitParam(name = "password", value = "密码", required = true)
    })
    @ResponseBody
    public R register(String admin, String password) {
        return loginServcie.register(admin, password);
    }

    /**
     * 用户密码登录
     *
     * @param admin
     * @param password
     * @return
     */
    @ApiOperation(value = "用户密码登录")
    @PostMapping(value = "/login")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "admin", value = "账号", required = true),
            @ApiImplicitParam(name = "password", value = "密码", required = true)
    })
    @ResponseBody
    public R login(String admin, String password) {
        return loginServcie.login(admin, password);
    }

    /**
     * 判断登录
     *
     * @return com.xingchen.utils.R
     * @date 2022/7/5 21:31
     */
    @ApiOperation(value = "判断登录")
    @GetMapping(value = "/isLogin")
    @ResponseBody
    public JwtUser isLogin(HttpServletRequest request) {
        return loginServcie.isLogin(request);
    }

    /**
     * 注销用户
     */
    @ApiOperation(value = "注销用户")
    @ResponseBody
    @GetMapping("/logout")
    public void logout(HttpSession session) {
        session.invalidate();
        System.out.println("logout执行了...");
    }
}
