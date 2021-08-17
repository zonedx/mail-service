package com.zone.mailservice.service.impl;

import com.zone.mailservice.pojo.Email;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Classname SimpleMailServiceImplTest
 * @Description
 * @Date 2021/8/18 12:33 上午
 * @Created by zone
 */

@SpringBootTest
@MapperScan("com.zone.mailservice.dao")
public class SimpleMailServiceImplTest {

    @Autowired
    private SimpleMailServiceImpl simpleMailService;

    @Test
    public void sendMailTest(){
        Email email = new Email(new String[]{"zonedx@163.com"},"生日祝福","生日快乐");
        simpleMailService.sendMail(email);
    }
}
