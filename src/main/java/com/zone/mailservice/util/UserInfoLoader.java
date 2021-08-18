package com.zone.mailservice.util;

import com.zone.mailservice.pojo.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

/**
 * @Classname UserInfoLoader
 * @Description
 * @Date 2021/8/17 7:28 下午
 * @Created by zone
 */

public class UserInfoLoader {

    private static final String PREFIX = "{";
    private static final String USER_INFO_JSON = "database/user-info.txt";
    private static final String sdf = "yyyy-MM-dd";

    public static void loadDataFromFile(HashMap<Integer, User> userInfo) throws IOException {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(USER_INFO_JSON);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String s;
        User user;
        while ((s = reader.readLine()) != null) {
            if (s.startsWith(PREFIX)){

                String[] splits = s.split(",");
                user = new User();
                //id唯一且存储在每条信息的起始位置
                user.setId(Integer.parseInt(s.substring(s.indexOf(":") + 1,s.indexOf(","))));
                for (int i = 1; i < splits.length; i ++){
                    switch (splits[i].substring(0,splits[i].indexOf(":"))){
                        case "name":
                            user.setName(removePunctuation(splits[i].substring(splits[i].indexOf(":") +1)));
                            break;
                        case "gender":
                            user.setGender(Integer.parseInt(splits[i].substring(splits[i].indexOf(":") + 1)));
                            break;
                        case "birthday":
                            String birthdayStr = removePunctuation(splits[i].substring(splits[i].indexOf(":") +1));
                            user.setBirthday(DateTimeUtils.strToDate(birthdayStr,sdf));
                            break;
                        case "email":
                            user.setEmail(removePunctuation(splits[i].substring(splits[i].indexOf(":") +1)));
                            break;
                        case "phone":
                            user.setPhone(removePunctuation(splits[i].substring(splits[i].indexOf(":") +1)));
                            break;
                        //extension info
                        default:{
                            break;
                        }
                    }
                }
                System.out.println(user);
                userInfo.put(user.getId(),user);
            }
        }

    }

    private static String removePunctuation(String str){
        return str.replace("\"","").replace("}","");
    }

    private static void loadDataFromDatabase(HashMap<Integer, User> userInfo){
        //TODO
        //mysql、redis or other data source.
    }
}
