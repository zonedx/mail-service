package com.zone.mailservice.service;

import com.zone.mailservice.common.ServerResponse;

/**
 * @Classname IMailService
 * @Description
 * @Date 2021/8/17 8:51 下午
 * @Created by zone
 */
public interface IMailService<T> {

    public ServerResponse<String> sendMail(T data);
}
