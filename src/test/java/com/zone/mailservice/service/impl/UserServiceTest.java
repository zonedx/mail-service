package com.zone.mailservice.service.impl;

import com.zone.mailservice.MailServiceApplication;
import com.zone.mailservice.common.ServerResponse;
import com.zone.mailservice.dao.UserMapper;
import com.zone.mailservice.pojo.User;
import com.zone.mailservice.service.impl.UserService;
import com.zone.mailservice.util.UserInfoLoader;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;

/**
 * @Classname UserServiceTest
 * @Description
 * @Date 2021/8/17 9:45 下午
 * @Created by zone
 */
@SpringBootTest
@MapperScan("com.zone.mailservice.dao")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void insert() throws IOException {
        HashMap<Integer, User> hashMap = new HashMap<>();
        UserInfoLoader.loadDataFromFile(hashMap);

        for (Integer key : hashMap.keySet()){
            User user = hashMap.get(key);
            //test insert select update
            ServerResponse response = userService.insert(user);
            System.out.println(response.getMsg());
        }

    }
}
