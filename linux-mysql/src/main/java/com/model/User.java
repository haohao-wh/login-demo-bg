package com.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassNmae User
 * @Discription
 * @Author wh
 * @Date 2020/10/1  18:42
 * @Version 1.0
 */
@Entity
@Data
public class User implements Serializable {
    @Id
    private String id;
    private String name;
    private String password;
    private Integer age;
    private Double salary;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date birth;

}
