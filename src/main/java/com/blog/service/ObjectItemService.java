package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.pojo.ObjectItem;

import java.util.List;

/**
 * @author a1002
 */
public interface ObjectItemService extends IService<ObjectItem> {
    void removeByPath(List<String> path);
}
