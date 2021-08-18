package com.zone.mailservice.util;

import com.zone.mailservice.common.ServerResponse;
import com.zone.mailservice.pojo.Email;
import com.zone.mailservice.pojo.User;
import com.zone.mailservice.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @Classname EmailGenerator
 * @Description
 * @Date 2021/8/18 10:03 下午
 * @Created by zone
 */
@Component
public class EmailGenerator {

    @Autowired
    private UserService userService;

    private static final String SDF = "MM-dd";
    private static final String SUBJECT = "生日快乐！";

    public Email[] newMails() {
        Date date = new Date(System.currentTimeMillis());
        ServerResponse<List<User>> response = userService.findUserByBirthday(DateTimeUtils.dateToStr(date, SDF));
        if (!response.isSuccess()) {
            return new Email[]{};
        }
        List<User> userList = response.getData();

        String[] tos = new String[userList.size()];
        Email[] newMails = new Email[userList.size()];
        for (int i = 0; i < userList.size(); i++) {
            tos[i] = userList.get(i).getEmail();

            newMails[i] = new Email();
            newMails[i].setTo(userList.get(i).getEmail());
            newMails[i].setSubject(SUBJECT);

            StringBuilder content = new StringBuilder();
            content.append("亲爱的：").append(userList.get(i).getName());
            if (userList.get(i).getGender() == 0){
                content.append("女士");
            }else{
                content.append("男士");
            }
            content.append(SUBJECT);
            newMails[i].setContent(content.toString());
        }

        return newMails;
    }
}
