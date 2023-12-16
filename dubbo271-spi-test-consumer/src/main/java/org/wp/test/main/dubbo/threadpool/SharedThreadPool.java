/*
 * Copyright © 2023 By wang-p,All rights reserved.
 */
package org.wp.test.main.dubbo.threadpool;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.threadpool.ThreadPool;
import org.apache.dubbo.common.utils.NamedThreadFactory;
import org.wp.test.main.dubbo.config.ThreadPoolConfig;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @class: SharedThreadPool
 * @desc: Dubbo Client端共享线程池SPI
 * @author: wang-p
 * @date: 2023/12/16
 * @version: 1.0.0
 * @modify:
 */
@Slf4j
public class SharedThreadPool implements ThreadPool {

    private volatile static ThreadPoolExecutor threadPoolExecutor;

    @Override
    public Executor getExecutor(URL url) {

        if (threadPoolExecutor == null) {
            initThreadPoolExecutor(url);
        }

        log.info("client using thread-pool:{}, url:{}", this.getClass().getSimpleName(), url);
        return threadPoolExecutor;

    }

    private void initThreadPoolExecutor(URL url) {

        if (threadPoolExecutor == null) {
            synchronized (SharedThreadPool.class) {
                if (threadPoolExecutor == null) {
                    threadPoolExecutor = new SharedThreadPoolExecutor(ThreadPoolConfig.corePoolSize,
                            ThreadPoolConfig.maxPoolSize, ThreadPoolConfig.keepAliveTime, TimeUnit.MILLISECONDS,
                            new LinkedBlockingQueue<>(ThreadPoolConfig.queueSize),
                            new NamedThreadFactory("DubboClientHandler-Shared-" + url.getAddress()),
                            new SharedRejectExecutionHandler());
                }
            }
        }
    }

}
