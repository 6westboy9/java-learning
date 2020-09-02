package com.westboy.util;

import lombok.Data;

import java.util.Date;

/**
 * @author pengbo
 * @since 2020/8/26
 */
@Data
public class UserDTO {
    private Integer id;
    private String name;
    private Integer age;
    private Date birthday;
}
