package com.quality.service;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;

public interface TaskQueueManage {
    Map<Long, ArrayBlockingQueue<?>> findAllTaskQueue();

    ArrayBlockingQueue<?> findTaskQueueById(Long id);

    void addTaskQueue(Long id, Integer maxTaskCount);

    void addTaskQueue(Long id);

    void removeTaskQueue(Long id);
}
