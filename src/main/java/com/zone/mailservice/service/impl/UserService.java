package com.zone.mailservice.service.impl;

import com.zone.mailservice.common.ServerResponse;
import com.zone.mailservice.dao.UserMapper;
import com.zone.mailservice.pojo.User;
import com.zone.mailservice.util.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Classname UserService
 * @Description
 * @Date 2021/8/17 8:49 下午
 * @Created by zone
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DataCheckService dataCheckService;

    private static final String SDF = "MM-dd";

    public ServerResponse<String> insert(User user){

        User oldUser = userMapper.selectByPrimaryKey(user.getId());
        if(oldUser != null){
            int updateRes = userMapper.updateByPrimaryKeySelective(user);
            if (updateRes == 0){
                return ServerResponse.createByErrorMessage("用户已存在，更新用户信息失败！");
            }
            return ServerResponse.createBySuccessMessage("用户已存在，更新用户信息成功!");
        }else{
            int insertRes = userMapper.insert(user);
            if (insertRes == 0){
                return ServerResponse.createByErrorMessage("插入用户信息失败！");
            }
            return ServerResponse.createBySuccessMessage("插入用户信息成功!");
        }
    }

    public ServerResponse<String> updateSendResult(User user){
        User oldUser = userMapper.selectByPrimaryKey(user.getId());
        if (oldUser == null){
            return ServerResponse.createByErrorMessage("用户不存在，请检查！");
        }

        int result = userMapper.updateByPrimaryKeySelective(user);

        if (result == 0){
            return ServerResponse.createByErrorMessage("更新用户信息失败！");
        }
        return ServerResponse.createBySuccessMessage("更新用户信息成功!");
    }

    public ServerResponse<String> updateByEmail(String email,Integer sendResult, Integer failureCount){
        int result = userMapper.updateByEmail(email,sendResult,failureCount);
        if (result == 0){
            return ServerResponse.createByErrorMessage("更新用户信息失败！");
        }
        return ServerResponse.createBySuccessMessage("更新用户信息成功!");
    }

    public ServerResponse<String> updateFailureCount(String email){
        int result = userMapper.updateFailureCount(email);
        if (result == 0){
            return ServerResponse.createByErrorMessage("更新用户信息失败！");
        }
        return ServerResponse.createBySuccessMessage("更新用户信息成功!");
    }

    public ServerResponse<User> findUserByEmail(String email){
        User user = userMapper.selectByEmail(email);
        if (user == null){
            return ServerResponse.createByErrorMessage("数据库出错！");
        }
        return ServerResponse.createBySuccess(user);
    }

    public ServerResponse<List<User>> findUserByBirthday(String birthday){
        List<User> userList = new ArrayList<>();
        HashMap<Integer,User> userInfo = dataCheckService.getUserInfo();
        if (userInfo.isEmpty()){
            return ServerResponse.createByErrorMessage("暂无用户数据");
        }

        for (Integer key : userInfo.keySet()){
            User user = userInfo.get(key);
            if (birthday.equals(DateTimeUtils.dateToStr(user.getBirthday(), SDF))){
                userList.add(user);
            }
        }
        if (userList.size() == 0){
            return ServerResponse.createByErrorMessage("今天没有用户生日");
        }

        return ServerResponse.createBySuccess(userList);
    }
}
