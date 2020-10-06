package com.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassNmae AUserDao
 * @Discription
 * @Author wh
 * @Date 2020/10/5  19:15
 * @Version 1.0
 */
@Data
@Entity
@Table(name = "a_user")
public class AUser {
    @Id
    private String id;
    private String name;
    private String salary;
    private int age;
    private String password;
}
