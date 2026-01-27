package com.example.demo;

import java.time.Duration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootApplication
@Slf4j
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        log.info("starts");
        StringRedisTemplate redisTemplate = ctx.getBean(StringRedisTemplate.class);
        String key = "aaaaa:hello";
        redisTemplate.opsForValue().set(key, "world", Duration.ofSeconds(10));
        String v = redisTemplate.opsForValue().get(key);
        log.info("key={}, value={}", key, v);
    }

}
