package com.example.demo;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.jvm.ExecutorServiceMetrics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * .
 *
 * @author lei.liu
 * @since 2025-12-20
 */
@Configuration
@Slf4j
public class ThreadPoolConfig {
    public static final String ThreadPool_NAME_BIZ_EXECUTOR = "bizExecutor";
    public static final String ThreadPool_NAME_ASYNC_EXECUTOR = "asyncExecutor";

    @Bean(ThreadPool_NAME_BIZ_EXECUTOR)
    public ThreadPoolTaskExecutor bizExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(30);
        executor.setQueueCapacity(90);
        executor.setThreadNamePrefix("biz-");
        executor.initialize();

        return executor;
    }

    //  使用监听器绑定监控，确保此时 MeterRegistry 已经是“完整体”
    @Bean
    public ApplicationListener<ApplicationReadyEvent> metricsBinderBizExecutor(
            MeterRegistry registry,
            @Qualifier(ThreadPool_NAME_BIZ_EXECUTOR) ThreadPoolTaskExecutor executor) {
        return event -> {
            ExecutorServiceMetrics.monitor(registry,
                    executor.getThreadPoolExecutor(),
                    ThreadPool_NAME_BIZ_EXECUTOR);
        };
    }

    @Bean(ThreadPool_NAME_ASYNC_EXECUTOR)
    public ThreadPoolExecutor asyncExecutor() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                5,
                20,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(100),
                r -> new Thread(r, "async-" + r.hashCode())
        );
        log.info("executor={}", executor);
        return executor;
    }

    //  使用监听器绑定监控，确保此时 MeterRegistry 已经是“完整体”
    @Bean
    public ApplicationListener<ApplicationReadyEvent> metricsBinderAsyncExecutor(
            MeterRegistry registry,
            @Qualifier(ThreadPool_NAME_ASYNC_EXECUTOR) ThreadPoolExecutor executor) {
        return event -> {
            ExecutorServiceMetrics.monitor(registry,
                    executor,
                    ThreadPool_NAME_ASYNC_EXECUTOR);
        };
    }

}
