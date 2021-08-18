package com.zone.mailservice.task;


import com.zone.mailservice.common.ServerResponse;
import com.zone.mailservice.pojo.Email;
import com.zone.mailservice.service.IMailService;
import com.zone.mailservice.service.impl.UserService;
import com.zone.mailservice.util.EmailGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Classname SendEmailTask
 * @Description
 * @Date 2021/8/18 10:05 下午
 * @Created by zone
 */
@Component
@Slf4j
public class SendEmailTask {

    @Autowired
    private EmailGenerator emailGenerator;
    @Autowired
    private IMailService simpleMailService;
    @Autowired
    private UserService userService;


//    @Scheduled(cron = "0 */1 * * * ?")  //for test
    //每天早上9.30执行任务
    @Scheduled(cron = "0 30 9 * * ?")
    public void sendMail(){
    Email[] emails = emailGenerator.newMails();
        if (emails.length == 0){
            log.info("当天没有员工生日");
        }

        for (int i = 0 ; i < emails.length;i++){
            ServerResponse<String> sendResult = null;
            try {
                sendResult = simpleMailService.sendMail(emails[i]);
            }catch (Exception e){//temp
                log.error("邮件发送服务出错，请检查邮箱地址或网络连接等信息：" + e.getMessage());
                sendResult = ServerResponse.createByErrorMessage(e.getMessage());
            }
            if (sendResult.isSuccess()){
                log.info("向" + emails[i].getTo() + "发送邮件成功！");
                userService.updateByEmail(emails[i].getTo(),1,0);
            }else{
                log.warn("向" + emails[i].getTo() + "发送邮件失败！");
                userService.updateFailureCount(emails[i].getTo());
            }
        }
    }
    //TODO 1、服务器宕机 错过当天9点30发邮件，导致少发   2、同一个员工发送失败次数>=3的处理

}
