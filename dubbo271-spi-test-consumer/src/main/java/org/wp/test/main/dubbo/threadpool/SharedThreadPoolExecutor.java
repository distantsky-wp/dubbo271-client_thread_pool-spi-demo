/*
 * Copyright © 2023 By wang-p,All rights reserved.
 */
package org.wp.test.main.dubbo.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * @class: SharedThreadPoolExecutor
 * @desc:
 * @author: wang-p
 * @date: 2023/12/16
 * @version: 1.0.0
 * @modify:
 */
@Slf4j
public class SharedThreadPoolExecutor extends ThreadPoolExecutor {

    private Thread lastIgnoreThread;

    public SharedThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                                    BlockingQueue<Runnable> workQueue,
                                    ThreadFactory threadFactory,
                                    RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    @Override

    public void shutdown() {
        protectExecutorShutdown(() -> {
            super.shutdown();
            return null;
        });
    }

    @Override
    public List<Runnable> shutdownNow() {
        List<Runnable> result = protectExecutorShutdown(() -> super.shutdownNow());
        return result != null ? result : Collections.emptyList();
    }

    /**
     * 只有当应用关闭时，才允许关闭该共享线程池
     *
     * @param supplier
     * @param <T>
     * @return
     */
    private <T> T protectExecutorShutdown(Supplier<T> supplier) {
        Thread currentThread = Thread.currentThread();
        if (Objects.equals(lastIgnoreThread, currentThread)) {
            return null;
        }

        if (isTriggerByShutdownHook(currentThread)) {
            log.info("shutdownNow is executing. current-thread={}", currentThread.getName());
            return supplier.get();
        }

        log.info("shutdownNow is not executing. current-thread={}", currentThread.getName());
        lastIgnoreThread = currentThread;
        return null;

    }

    private boolean isTriggerByShutdownHook(Thread thread) {

        if (Objects.equals(thread.getName(), "DubboShutdownHook")
                || Objects.equals(thread.getName(), "SpringContextShutdownHook")) {
            // 只有当应用关闭时，才允许关闭共享的线程池
            return true;
        }

        return false;

    }

    @Override
    protected void terminated() {
        log.info("SharedThreadPool is terminated");
        super.terminated();
    }

}
