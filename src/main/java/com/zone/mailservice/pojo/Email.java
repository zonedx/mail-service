package com.zone.mailservice.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Classname Email
 * @Description
 * @Date 2021/8/18 12:28 上午
 * @Created by zone
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Email implements Serializable {

    private String[] tos;

    private String subject;

    private String content;

}
