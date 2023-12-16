/*
 * Copyright © 2023 By wang-p,All rights reserved.
 */
package org.wp.test.main.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wp.test.main.call.CallTestService;

import java.time.LocalDateTime;

/**
 * @class: TestController
 * @desc: 
 * @author: wang-p
 * @date: 2023/12/16
 * @version: 1.0.0
 * @modify: 
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private CallTestService service;

    @GetMapping("/callProvider")
    public String callProvider () {
        String msg = "测试时间：" + LocalDateTime.now();
        log.info(msg);
        return service.testCall() + "\r\n" + msg;
    }

}
