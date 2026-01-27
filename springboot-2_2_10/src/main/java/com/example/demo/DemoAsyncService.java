package com.example.demo;

import java.util.concurrent.ThreadPoolExecutor;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

/**
 * .
 *
 * @author lei.liu
 * @since 2025-12-20
 */
@Service
@Slf4j
public class DemoAsyncService {

    @Autowired
    @Qualifier(ThreadPoolConfig.ThreadPool_NAME_ASYNC_EXECUTOR)
    public ThreadPoolExecutor threadPoolExecutor;

    @Autowired
    @Qualifier(ThreadPoolConfig.ThreadPool_NAME_BIZ_EXECUTOR)
    public ThreadPoolTaskExecutor threadPoolTaskExecutorBiz;

    @Async(value = ThreadPoolConfig.ThreadPool_NAME_ASYNC_EXECUTOR)
    public void callAsyncTask1() {
        log.info("completed={}, pool={}",
                threadPoolExecutor.getCompletedTaskCount(),
                threadPoolExecutor.getPoolSize()
        );
        log.info("starts");
        try {
            int randomMills = RandomUtils.nextInt(0, 100);
            Thread.sleep(200L + randomMills);
        } catch (InterruptedException e) {
        }
        log.info("end");
    }

    @Async(value = ThreadPoolConfig.ThreadPool_NAME_ASYNC_EXECUTOR)
    public void callAsyncTask2LongDuration() {
        log.info("callAsyncTask2LongDuration.completed={}, pool={}",
                threadPoolExecutor.getCompletedTaskCount(),
                threadPoolExecutor.getPoolSize()
        );
        log.info("starts");
        try {
            int randomMills = RandomUtils.nextInt(1, 100);
            Thread.sleep(8000L + randomMills);
        } catch (InterruptedException e) {
        }
        log.info("end");
    }

    @Async(value = ThreadPoolConfig.ThreadPool_NAME_BIZ_EXECUTOR)
    public void callAsyncTaskBiz() {
        log.info("biz.completed={}, pool={}",
                threadPoolTaskExecutorBiz.getThreadPoolExecutor().getCompletedTaskCount(),
                threadPoolTaskExecutorBiz.getThreadPoolExecutor().getPoolSize()
        );
        log.info("biz starts");
        try {
            int randomMills = RandomUtils.nextInt(0, 100);
            Thread.sleep(200 + randomMills);
        } catch (InterruptedException e) {
        }
        log.info("biz end");
    }

}
