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
public class Manager {
    private Long id;
    private String account;
    private String password;
}
