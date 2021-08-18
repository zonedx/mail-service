package com.zone.mailservice.dao;

import com.zone.mailservice.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Classname UserMapper
 * @Description
 * @Date 2021/8/17 8:51 下午
 * @Created by zone
 */
@Repository
public interface UserMapper {
    User selectByPrimaryKey(Integer id);

    User selectByEmail(String email);

    int deleteByPrimaryKey(Integer id);

    int insert(User user);

    int updateByPrimaryKeySelective(User user);

    int updateByEmail(@Param("email") String email,@Param("sendResult")Integer sendResult,@Param("failureCount")Integer failureCount);

    int updateFailureCount(String email);

}
