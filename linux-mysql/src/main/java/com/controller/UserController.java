package com.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.enums.ResponseCode;
import com.form.LoginForm;
import com.model.User;
import com.service.UserService;
import com.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassNmae UserController
 * @Discription
 * @Author wh
 * @Date 2020/10/1  19:16
 * @Version 1.0
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    RedisTemplate redisTemplate;

    @RequestMapping("queryAllUser")
    public ResultUtil queryAllUser() {
        List<User> allUser = userService.findAllUser();
        return ResultUtil.success(allUser, "查询所有数据");
    }

    @RequestMapping("queryUserById")
    public ResultUtil queryUserById(@RequestBody String id) {
        User userById = userService.findUserById(JSONUtil.parseObj(id).getStr("id"));
        return ResultUtil.success(userById, "查询一条数据成功");
    }

    @RequestMapping("saveUser")
    public ResultUtil saveUser(@RequestBody User user) {
        User user1 = userService.saveUser(user);
        return ResultUtil.success(user1, "保存" + user1.getName() + "成功");
    }

    @RequestMapping(value = "login")
    public ResultUtil login(@RequestBody LoginForm loginForm) {
        String code = (String) redisTemplate.opsForValue().get(loginForm.getCaptchaCode());
        if (StrUtil.isEmpty(code) || !code.equals(loginForm.getCode())) {
            return ResultUtil.fail(ResponseCode.ERROR.val(), "验证码错误或者失效");
        }
        User userByName = userService.findUserByName(loginForm.getUsername());
        if (!(userByName != null && loginForm.getPassword().equals(userByName.getPassword()))) {
            return ResultUtil.fail(ResponseCode.ERROR.val(), "密码错误");
        }
        return ResultUtil.success(userByName, loginForm.getUsername() + "登录成功");
    }

    @RequestMapping("code")
    @ResponseBody
    public void code(HttpServletResponse response) throws IOException {
        //定义图形验证码的长、宽、验证码字符数、干扰元素个数
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(200, 100, 4, 20);
        ServletOutputStream outputStream = response.getOutputStream();
        String code = captcha.getCode();
        String uuid = IdUtil.randomUUID();
        System.err.println(uuid);
        redisTemplate.opsForValue().set(uuid, code, 3 * 60, TimeUnit.SECONDS);
        Cookie captchaCode = new Cookie("CaptchaCode", uuid);
        captchaCode.setMaxAge(6060);
        captchaCode.setPath("/");
        response.addCookie(captchaCode);
        captcha.write(outputStream);
        outputStream.close();
    }

}

