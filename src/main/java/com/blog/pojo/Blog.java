package com.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author a1002
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    private Long id;
    private String name;
    private String blogPath;
    private String img;
}
