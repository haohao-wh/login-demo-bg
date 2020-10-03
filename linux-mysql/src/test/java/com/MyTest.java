package com;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

    @Test
    public void test1(){
        String result2= HttpUtil.get("https://www.baidu.com", CharsetUtil.CHARSET_UTF_8);
        System.out.println(result2);
    }
    @Test
    public void test2(){

        JSONObject s = JSONUtil.parseObj("{\"id\":\"1\"}");
        System.out.println(s.get("id"));
    }
}
