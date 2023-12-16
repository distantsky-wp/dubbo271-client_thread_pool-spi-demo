/*
 * Copyright © 2023 By wang-p,All rights reserved.
 */
package org.wp.test.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

/**
 * @class: Application
 * @desc:
 * @author: wang-p
 * @date: 2023/12/16
 * @version: 1.0.0
 * @modify:
 */
@SpringBootApplication
@ImportResource(locations = "classpath:service-consumer.xml")
public class ApplicationConsumer {

    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        context = SpringApplication.run(ApplicationConsumer.class, args);
        // 用于测试应用正常关闭时才允许关闭共享线程池的逻辑是否正确
        // while (true) {
        //     if (System.currentTimeMillis() - start > 30000)
        //         System.exit(0);
        // }
    }

    // 用于logback关闭Hook监测Spring本身是否已经关闭，保证所有日志能正常输出
    public static boolean isActive() {
       return context.isActive();
    }
}
