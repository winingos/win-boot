package com.win.jdbc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Administrator on 17/12/10.
 * Create table 'user'(
 * ID INT PRIMARY KEY NOT NULL,
 * NAME VARCHAR(255) NOT NULL,
 * AGE INT NOT NULL,
 * SEX VARCHAR(255) NOT NULL );
 *
 * sqlite
 */
@Getter
@Setter
@AllArgsConstructor
public class User {
    public User(){};
    private int id;
    private String name;
    private int age;
    private String sex;
}
