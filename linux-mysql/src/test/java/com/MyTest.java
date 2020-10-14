package com;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.model.User;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
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
        Stream.of("file/aaa", "bcb", "caaac", "dad").map(String::length).forEach(System.out::println);
    }
    @Test
    public void test5() throws IOException {

        TemplateExportParams params = new TemplateExportParams(
                "WEB-INF/doc/专项支出用款申请书_map.xls");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("date", "2014-12-25");
        map.put("money", 2000000.00);
        map.put("upperMoney", "贰佰万");
        map.put("company", "执笔潜行科技有限公司");
        map.put("bureau", "财政局");
        map.put("person", "JueYue");
        map.put("phone", "1879740****");
        List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();
        for (int i = 0; i < 4; i++) {
            Map<String, String> lm = new HashMap<String, String>();
            lm.put("id", i + 1 + "");
            lm.put("zijin", i * 10000 + "");
            lm.put("bianma", "A001");
            lm.put("mingcheng", "设计");
            lm.put("xiangmumingcheng", "EasyPoi " + i + "期");
            lm.put("quancheng", "开源项目");
            lm.put("sqje", i * 10000 + "");
            lm.put("hdje", i * 10000 + "");

            listMap.add(lm);
        }
        map.put("maplist", listMap);

        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        File savefile = new File("D:/excel/");
        if (!savefile.exists()) {
            savefile.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream("D:/excel/专项支出用款申请书_map.xls");
        workbook.write(fos);
        fos.close();
    }
}
