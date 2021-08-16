package com.zone.mailservice.common;

/**
 * @Classname ResponseCode
 * @Description
 * @Date 2021/8/17 12:06 上午
 * @Created by zone
 */
public enum  ResponseCode {

    /**
     * SUCCESS 成功
     * ERROR {@link ServerResponse} ServerResponse.createByError()默认返回1
     * _500 服务器内部错误
     */
    SUCCESS(0,"SUCCESS"),
    ERROR(1,"ERROR"),
    _500(500,"SERVER ERROR,INTERFACE EXCEPTION");


    private final int code;
    private final String desc;

    ResponseCode(int code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode(){
        return code;
    }
    public String getDesc(){
        return desc;
    }
}
