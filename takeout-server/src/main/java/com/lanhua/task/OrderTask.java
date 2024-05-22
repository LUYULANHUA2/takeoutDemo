package com.lanhua.task;


import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class OrderTask {

    /**
     *处理超时订单
     */
    @Scheduled(cron = "0 * * * * ?")
    public void processOrder(){
        log.info("处理超时订单的时间，{}", LocalDateTime.now());

        LocalDateTime time = LocalDateTime.now().plusMinutes(-15);

    }

}
