package com.quality.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "task-queue")
public class TaskQueueProperties {
    Integer defaultMaxQueueSize;
}
