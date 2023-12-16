/*
 * Copyright © 2023 By wang-p,All rights reserved.
 */
package org.wp.test.main.dubbo.threadpool;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.remoting.transport.dispatcher.WrappedChannelHandler;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @class: SharedRejectExecutionHandler
 * @desc:
 * @author: wang-p
 * @date: 2023/12/16
 * @version: 1.0.0
 * @modify:
 */
@Slf4j
public class SharedRejectExecutionHandler implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
        ExecutorService handlerSharedExecutor = getHandlerSharedExecutor();
        if (handlerSharedExecutor != null) {
            log.info("reject policy is Triggered, default thread-pool[SHARED_EXECUTOR] execute the task");
            handlerSharedExecutor.execute(r);
        } else {
            log.info("reject policy is Triggered, current thread execute the task");
            r.run();
        }
    }

    protected ExecutorService getHandlerSharedExecutor() {
        Field sharedExecutorField = ReflectionUtils.findField(WrappedChannelHandler.class,
                "SHARED_EXECUTOR",
                ExecutorService.class);
        try {
            sharedExecutorField.setAccessible(true);
            return (ExecutorService) sharedExecutorField.get(WrappedChannelHandler.class);
        } catch (Throwable e) {
            log.error("Acquiring default thread-pool[SHARED_EXECUTOR] is failed", e);
            return null;
        }
    }

}
