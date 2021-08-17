package com.zone.mailservice.service.impl;

import com.zone.mailservice.common.ServerResponse;
import com.zone.mailservice.dao.UserMapper;
import com.zone.mailservice.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
