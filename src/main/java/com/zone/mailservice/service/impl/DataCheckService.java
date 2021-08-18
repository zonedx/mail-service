package com.zone.mailservice.service.impl;

import com.zone.mailservice.common.ServerResponse;
import com.zone.mailservice.pojo.User;
import com.zone.mailservice.util.UserInfoLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;

/**
 * @Classname DataCheckService
 * @Description
 * @Date 2021/8/18 17:50
 * @Created by zone
 */
@Component
@Order(value = 1)
@Slf4j
@EnableScheduling
public class DataCheckService implements ApplicationRunner {

    private HashMap<Integer, User> userInfo = new HashMap<>();

    @Autowired
    private UserService userService;

    @Override
    public void run(ApplicationArguments args){
        log.info("初始化项目加载用户信息");
        checkData();
    }

    //每天凌晨1点更新数据
    @Scheduled(cron = "0 0 1 * * ?")
    private void checkData(){
        //1、获取文件数据
        try {
            UserInfoLoader.loadDataFromFile(userInfo);
        } catch (IOException e) {
            log.error("获取文件数据失败：",e);
        }

        //2、存数据库
        for (Integer key : userInfo.keySet()){
            User user = userInfo.get(key);
            ServerResponse response = userService.insert(user);
            if (!response.isSuccess()) {
                log.warn(response.getMsg());
            }
        }
        log.info("数据检查完成！");
    }

}
