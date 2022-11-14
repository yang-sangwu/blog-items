package com.blog.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.blog.pojo.User;
import com.blog.service.UserService;
import com.blog.utils.MailUtils;
import com.blog.utils.R;
import com.blog.utils.ValidateCodeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author a1002
 */
@Api(tags = "user")
@Slf4j
@RestController
//@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @ApiOperation(value = "修改用户")
    @PutMapping("/update")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public R update(Long id, String username, String password, String img, String blogPath, String grade) {
        userService.updateByID(id, username, password, img, blogPath, grade);
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
    @GetMapping("/findUserById")
    @ResponseBody
    public R findUserById(Long id) {
        return R.success(userService.getById(id));
    }

    @ApiOperation(value = "发送验证码")
    @PostMapping("/sendMail")
    @ResponseBody
    public R sendMail(String mail) throws Exception {
        String code = ValidateCodeUtils.generateValidateCode4String(4);
        MailUtils.sendMail(mail, code);
        return R.success("发送成功！");

    }

    @ApiOperation(value = "修改用户权限")
    @ResponseBody
    @PostMapping("/status")
    public R status(Integer status, @RequestParam List<Long> ids) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(User::getId, ids);
        List<User> list = userService.list(queryWrapper);
        List<User> list1 = list.stream().map((item) -> {
            item.setStatus(status);
            return item;
        }).collect(Collectors.toList());
        userService.updateBatchById(list1);
        return R.success(list1);
    }

}
