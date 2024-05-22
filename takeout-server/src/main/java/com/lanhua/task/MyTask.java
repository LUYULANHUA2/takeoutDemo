package com.lanhua.task;


import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.zip.DataFormatException;

/**
 * 自定义定时任务类
 */

@Slf4j
@Component
public class MyTask {


    @Scheduled(cron = "0/5 * * * * ?")
    public void executeTask(){
        log.info("定时输出，{}",new Date());
    }

}
