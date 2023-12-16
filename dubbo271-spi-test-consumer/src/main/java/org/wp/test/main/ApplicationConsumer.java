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
        // while (true) {
        //     if (System.currentTimeMillis() - start > 30000)
        //         System.exit(0);
        // }
    }

    public static boolean isActive() {
       return context.isActive();
    }
}
