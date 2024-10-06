package com.quality.service;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;

public interface TaskQueueManage {
    Map<Long, ArrayBlockingQueue<Future<String>>> findAllTaskQueue();

    ArrayBlockingQueue<Future<String>> findTaskQueueById(Long id);

    void addTaskQueue(Long id, Integer maxTaskCount);

    void addTaskQueue(Long id);

    void removeTaskQueue(Long id);
}
