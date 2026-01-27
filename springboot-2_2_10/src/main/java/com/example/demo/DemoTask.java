package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * .
 *
 * @author lei.liu
 * @since 2025-12-20
 */
@Component
public class DemoTask {
    @Autowired
    private DemoAsyncService demoAsyncService;

    @Scheduled(fixedDelay = 1000 * 5)
    public void taskAsync() {
        demoAsyncService.callAsyncTask1();
    }

    @Scheduled(fixedRate = 1000 * 8)
    public void taskAsync2() {
        demoAsyncService.callAsyncTask2LongDuration();
    }

    @Scheduled(fixedDelay = 1000 * 5)
    public void taskBiz() {
        demoAsyncService.callAsyncTaskBiz();
    }
}
