package com.zone.mailservice.pojo;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @Classname User
 * @Description
 * @Date 2021/8/17 7:27 下午
 * @Created by zone
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements Serializable {
    private Integer id;
    private String name;
    private Integer gender;
    private Date birthday;
    private String email;
    private String phone;

    //这三个属性实际上应该单独抽出来放到新表中的，这里由于时间关系直接放一起了
    private Integer sendResult = 0;
    private Integer failureCount = 0;
    private Date updateTime;
}
