package com.blog.controller;

import com.blog.pojo.User;
import com.blog.service.UserService;
import com.blog.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author a1002
 */
@Api(tags = "manager")
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @ApiOperation(value = "test")
    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "hello!";
    }

//    @PostMapping("/login")
//    public ResponseResult login(@RequestBody User user) {
//        //登录
//        return loginServcie.login(user);
//    }

//    @ApiOperation(value = "login")
//    @PostMapping("/login")
//    @ResponseBody
//    public R login(String username, String password) {
//        //获取当前用户
//        Subject subject = SecurityUtils.getSubject();
//        //封装用户的登陆数据
//        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
//        try {
//            subject.login(token);//执行登陆方法，如果没有异常就说明OK了
//            return R.success("success");
//        } catch (UnknownAccountException e) {//用户名不存在
//            return R.error("用户名错误");
//        } catch (IncorrectCredentialsException e) {
//            return R.error("密码错误");
//        }
//    }

    @ApiOperation(value = "增加")
    @PostMapping("/save")
    @ResponseBody
    public R save(User user) {
        userService.save(user);
        return R.success("success");
    }

    @ApiOperation(value = "修改用户")
    @PutMapping("/update")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public R update(@RequestBody User user) {
        userService.updateById(user);
        return R.success("success");
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("/delete")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public R delete(Long id) {
        userService.removeById(id);
        return R.success("success");
    }

    @ApiOperation(value = "根据id查询用户")
    @DeleteMapping("/findUserById")
    @ResponseBody
    public R findUserById(Long id) {
        userService.getById(id);
        return R.success("success");
    }


}
