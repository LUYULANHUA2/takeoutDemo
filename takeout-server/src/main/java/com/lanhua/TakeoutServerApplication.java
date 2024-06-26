package com.lanhua;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling//开启任务调度
@EnableCaching
public class TakeoutServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TakeoutServerApplication.class, args);
    }

}
