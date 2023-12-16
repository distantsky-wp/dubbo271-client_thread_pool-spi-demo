/*
 * Copyright © 2023 By wang-p,All rights reserved.
 */
package org.wp.test.main.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.wp.test.main.call.service.TestService;

/**
 * @class: TestServiceImpl
 * @desc:
 * @author: wang-p
 * @date: 2023/12/16
 * @version: 1.0.0
 * @modify:
 */
@Slf4j
@Service
public class TestServiceImpl implements TestService {

    @Override
    public String getMessage(String caller) {
        log.info("访问到了Dubbo-Provider ！！！");
        return "访问者为：" + caller;
    }

}
