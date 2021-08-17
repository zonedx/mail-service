package com.zone.mailservice.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Classname User
 * @Description
 * @Date 2021/8/17 7:27 下午
 * @Created by zone
 */
@Data
public class User {
    private int id;
    private String name;
    private int gender;
    private Date birthday;
    private String email;
    private String phone;

    //这三个属性实际上应该单独抽出来放到新表中的，这里由于时间关系直接放一起了
    private int sendResult;
    private int failureCount;
    private Date updateTime;
}
