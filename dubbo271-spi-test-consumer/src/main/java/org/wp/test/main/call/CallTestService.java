/*
 * Copyright © 2023 By wang-p,All rights reserved.
 */
package org.wp.test.main.call;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wp.test.main.call.service.TestService;

import java.time.LocalDateTime;

/**
 * @class: call
 * @desc:
 * @author: wang-p
 * @date: 2023/12/16
 * @version: 1.0.0
 * @modify:
 */
@Service
public class CallTestService {

    @Autowired
    private TestService testService;

    public String testCall() {
        return testService.getMessage("wp于" + LocalDateTime.now());
    }

}
