package com.sandalen.jwts.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Service
public class ScheduleService {
    //秒 分 时 月中的天 月 周中的天
    @Scheduled(cron = "0 0/2 9-17 * * ?")
    public void checkErrorData(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("我执行拉"+simpleDateFormat.format(System.currentTimeMillis()));
    }
}
