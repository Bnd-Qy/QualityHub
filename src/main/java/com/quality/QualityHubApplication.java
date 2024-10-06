package com.quality;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


@SpringBootApplication
public class QualityHubApplication {
    public static void main(String[] args) {
        SpringApplication.run(QualityHubApplication.class, args);
    }
}
