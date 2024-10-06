package com.quality.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

@Configuration
public class ThreadPoolConfig {

    @Bean
    public Executor taskThreadPool() {
        return new ThreadPoolExecutor(
                16, 33, 30,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(15),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
    }
}
