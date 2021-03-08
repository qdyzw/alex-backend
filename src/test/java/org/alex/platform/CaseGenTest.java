package org.alex.platform;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.alex.platform.enums.CaseRule;
import org.alex.platform.exception.BusinessException;
import org.alex.platform.generator.Generator;
import org.alex.platform.generator.Main;
import org.alex.platform.util.CommandUtil;
import org.alex.platform.util.FileUtil;
import org.alex.platform.util.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CaseGenTest {
    @Autowired
    Generator generator;
    @Autowired
    Main main;

    @Test
    public void doStringTest() throws Exception {
        String key = "name";
        String desc = "姓名";
        String type = "String";

        JSONObject publicConfig = new JSONObject();
        publicConfig.put("allowNull", false);
        publicConfig.put("allowRepeat", false);

        JSONObject privateConfig = new JSONObject();
        privateConfig.put("allowIllegal", false);
        privateConfig.put("allowEmpty", false);
        privateConfig.put("minLen", 2);
        privateConfig.put("maxLen", 2);

        System.out.println(generator.genSingleField(key, desc, type, publicConfig, privateConfig));
    }

    @Test
    public void doNumberTest() throws Exception {
        String key = "name";
        String desc = "姓名";
        String type = "Number";

        JSONObject publicConfig = new JSONObject();
        publicConfig.put("allowNull", false);
        publicConfig.put("allowRepeat", false);

        JSONObject privateConfig = new JSONObject();
        privateConfig.put("min", -0.1);
        privateConfig.put("max", 100);

        System.out.println(generator.genSingleField(key, desc, type, publicConfig, privateConfig));
    }

    @Test
    public void doInDbTest() throws Exception {
        String key = "name";
        String desc = "姓名";
        String type = "NotInDb";

        JSONObject publicConfig = new JSONObject();
        publicConfig.put("allowNull", false);
        publicConfig.put("allowRepeat", false);

        JSONObject privateConfig = new JSONObject();
        privateConfig.put("dbId", 1);
        privateConfig.put("sql", "select `desc` from t_db");
        privateConfig.put("elementType", "str");

        System.out.println(generator.genSingleField(key, desc, type, publicConfig, privateConfig));
    }

    @Test
    public void doInArrayTest() throws Exception {
        String key = "name";
        String desc = "姓名";
        String type = "notInArray";

        JSONObject publicConfig = new JSONObject();
        publicConfig.put("allowNull", false);
        publicConfig.put("allowRepeat", false);

        JSONObject privateConfig = new JSONObject();
        JSONArray array = new JSONArray();
        array.add(1);
        array.add(1);
        array.add(1);
        privateConfig.put("value", array);
        privateConfig.put("elementType", "integer");

        System.out.println(generator.genSingleField(key, desc, type, publicConfig, privateConfig));
    }

    @Test
    public void testMain() throws Exception {
        String jsonStr = FileUtil.readByBuffer("C:\\Users\\beix\\IdeaProjects\\platform\\src\\main\\resources\\template\\用户注册接口自动生成用例约束示例.json", StandardCharsets.UTF_8);
        JSONObject schema = JSONObject.parseObject(jsonStr);
//        System.out.println(JSON.toJSONString(main.generateCase(schema, CaseRule.ORT),
//                SerializerFeature.DisableCircularReferenceDetect,
//                SerializerFeature.WriteMapNullValue));
        FileOutputStream fileOutputStream = new FileOutputStream(new File("C:\\Users\\beix\\Desktop\\1.json"));
        JSON.writeJSONString(fileOutputStream, main.generateCase(schema, CaseRule.ORT), SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteMapNullValue, SerializerFeature.PrettyFormat);
    }

    @Test
    public void test1() throws Exception {
        JSONObject object = new JSONObject();
        object.put("123", "123");
        JSONArray array = new JSONArray();
        array.add(object);
        System.out.println(array);
        object = null;
        System.out.println(array);
    }

    @Test
    public void test2() {
        String cmd = "python src\\main\\resources\\python\\ort.py";
        System.out.println(CommandUtil.exec(cmd));
    }
}