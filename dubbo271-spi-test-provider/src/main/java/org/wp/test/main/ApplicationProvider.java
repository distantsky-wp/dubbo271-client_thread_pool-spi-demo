/*
 * Copyright © 2023 By wang-p,All rights reserved.
 */
package org.wp.test.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
@ImportResource(locations = "classpath:service-provider.xml")
public class ApplicationProvider {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationProvider.class, args);
    }

}
