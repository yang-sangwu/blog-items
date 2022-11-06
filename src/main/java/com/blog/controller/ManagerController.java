package com.blog.controller;

import com.blog.service.ManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author a1002
 */
@Api(tags = "manager")
@Slf4j
@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Resource
    private ManagerService managerService;

    @ApiOperation(value = "test")
    @PostMapping
    @ResponseBody
    public void test() {

    }

}
