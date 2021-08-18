package com.zone.mailservice.util;

import com.zone.mailservice.pojo.Email;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Classname EmailGeneratorTest
 * @Description
 * @Date 2021/8/18 10:50 下午
 * @Created by zone
 */
@SpringBootTest
@MapperScan("com.zone.mailservice.dao")
public class EmailGeneratorTest {

    @Autowired
    private EmailGenerator emailGenerator;

    @Test
    public void newMailsTest() {
        Email[] emails = emailGenerator.newMails();
        System.out.println(emails.length);
    }

}
