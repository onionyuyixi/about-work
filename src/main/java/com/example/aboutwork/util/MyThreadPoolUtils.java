package com.example.aboutwork.util;

import lombok.Getter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

import io.netty.util.concurrent.DefaultThreadFactory;

@Getter
public class MyThreadPoolUtils {

    private final ThreadPoolTaskExecutor executorService = new ThreadPoolTaskExecutor();

    private MyThreadPoolUtils() {
        int coreNum = Runtime.getRuntime().availableProcessors();
        executorService.setMaxPoolSize(2 * coreNum + 1);
        executorService.setCorePoolSize(coreNum);
        executorService.setQueueCapacity(16);
        executorService.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executorService.setWaitForTasksToCompleteOnShutdown(true);
        executorService.setThreadFactory(new DefaultThreadFactory("myThreadPool"));
    }

    public static MyThreadPoolUtils getInstance() {
        return MyThreadPoolHolder.INSTANCE;
    }

    private static class MyThreadPoolHolder {
        private final static MyThreadPoolUtils INSTANCE = new MyThreadPoolUtils();
    }

}
