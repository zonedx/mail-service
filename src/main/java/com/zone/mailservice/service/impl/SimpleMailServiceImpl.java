package com.zone.mailservice.service.impl;

import com.zone.mailservice.common.ServerResponse;
import com.zone.mailservice.pojo.Email;
import com.zone.mailservice.service.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @Classname MailServiceImpl
 * @Description
 * @Date 2021/8/17 8:52 下午
 * @Created by zone
 */
@Service
public class SimpleMailServiceImpl implements IMailService<Email> {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public ServerResponse<String> sendMail(Email email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setSubject(email.getSubject());
        message.setTo(email.getTo());
        message.setText(email.getContent());

        javaMailSender.send(message);

        return ServerResponse.createBySuccessMessage("发送成功！");
    }
}
