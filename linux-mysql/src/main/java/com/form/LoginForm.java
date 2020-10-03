package com.form;

import lombok.Data;

/**
 * @ClassNmae LoginForm
 * @Discription
 * @Author wh
 * @Date 2020/10/2  19:52
 * @Version 1.0
 */
@Data
public class LoginForm {

    private String username;
    private String password;
    private String code;
    private String captchaCode;

}
