package com.zone.mailservice.util;

import com.zone.mailservice.pojo.User;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.HashMap;

/**
 * @Classname UserInfoLoaderTest
 * @Description
 * @Date 2021/8/17 7:42 下午
 * @Created by zone
 */
@SpringBootTest
@MapperScan("com.zone.mailservice.dao")
public class UserInfoLoaderTest {

    @Test
    public void loadDataFromFileTest() throws IOException {
        HashMap<Integer, User> hashMap = new HashMap<>();
        UserInfoLoader.loadDataFromFile(hashMap);
    }
}
