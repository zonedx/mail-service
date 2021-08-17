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

    int deleteByPrimaryKey(Integer id);

    int insert(User user);

    int updateByPrimaryKeySelective(User user);

    int updateFailureCount(Integer id);
}
