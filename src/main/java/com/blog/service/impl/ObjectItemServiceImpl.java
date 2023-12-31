package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.mapper.ObjectItemMapper;
import com.blog.pojo.ObjectItem;
import com.blog.service.ObjectItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author a1002
 */
@Service
public class ObjectItemServiceImpl extends ServiceImpl<ObjectItemMapper, ObjectItem> implements ObjectItemService {

    @Autowired
    private ObjectItemService objectItemService;

    @Override
    public void removeByPath(List<String> path) {
        LambdaQueryWrapper<ObjectItem> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(ObjectItem::getObjectName, path);
        objectItemService.remove(queryWrapper);

    }
}
