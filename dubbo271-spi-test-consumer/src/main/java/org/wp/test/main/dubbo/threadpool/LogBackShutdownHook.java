/*
 * Copyright © 2023 By wang-p,All rights reserved.
 */
package org.wp.test.main.dubbo.threadpool;

import ch.qos.logback.core.hook.ShutdownHookBase;
import lombok.SneakyThrows;
import org.wp.test.main.ApplicationConsumer;

/**
 * @class: LogBackShutdownHook
 * @desc:
 * @author: wang-p
 * @date: 2023/12/16
 * @version: 1.0.0
 * @modify:
 */
public class LogBackShutdownHook extends ShutdownHookBase {

    @SneakyThrows
    @Override
    public void run() {
        while (ApplicationConsumer.isActive()) {
            Thread.sleep(500);
        }
        super.stop();
    }

}
