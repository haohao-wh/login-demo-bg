package com;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.stream.Stream;

/**
 * @ClassNmae MyTest
 * @Discription
 * @Author wh
 * @Date 2020/10/1  19:20
 * @Version 1.0
 */
@SpringBootTest(classes = SpringLinuxMysqlApp.class)
@RunWith(SpringRunner.class)
public class MyTest {

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void test1() {
        String result2 = HttpUtil.get("https://www.baidu.com", CharsetUtil.CHARSET_UTF_8);
        System.out.println(result2);
    }

    @Test
    public void test2() {

        JSONObject s = JSONUtil.parseObj("{\"id\":\"1\"}");
        System.out.println(s.get("id"));
    }

    @Test
    public void test3() {
        User user = new User();
        user.setId("1234");
        user.setName("aa");
        user.setBirth(new Date());
        redisTemplate.opsForValue().set("123", JSONUtil.toJsonStr(user));
        Object o = redisTemplate.opsForValue().get("123");
        JSONObject jsonObject = JSONUtil.parseObj(o);
        User user1 = jsonObject.toBean(User.class);
        System.out.println(user1);
    }

    @Test
    public void test4() {
        Stream.of("aaa", "bcb", "caaac", "dad").map(String::length).forEach(System.out::println);
    }
}
