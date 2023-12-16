/*
 * Copyright © 2023 By wang-p,All rights reserved.
 */
package org.wp.test.main.dubbo.config;

import lombok.Getter;

/**
 * @class: ThreadPoolConfig
 * @desc: Dubbo Client端共享线程池配置项
 * @author: wang-p
 * @date: 2023/12/16
 * @version: 1.0.0
 * @modify:
 */
@Getter
public class ThreadPoolConfig {

    public static int corePoolSize = 10;
    public static int maxPoolSize = 100;
    public static long keepAliveTime = 30000;
    public static int queueSize = 10000;

}
