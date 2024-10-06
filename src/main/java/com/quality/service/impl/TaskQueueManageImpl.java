package com.quality.service.impl;

import com.quality.config.TaskQueueProperties;
import com.quality.service.TaskQueueManage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class TaskQueueManageImpl implements TaskQueueManage {
    private Map<Long, ArrayBlockingQueue<?>> taskQueuePool;

    private final TaskQueueProperties taskQueueProperties;

    public TaskQueueManageImpl(TaskQueueProperties taskQueueProperties) {
        this.taskQueueProperties = taskQueueProperties;
    }

    @PostConstruct
    public void initTaskQueuePool() {
        taskQueuePool = new ConcurrentHashMap<>();
    }

    @Override
    public Map<Long, ArrayBlockingQueue<?>> findAllTaskQueue() {
        return taskQueuePool;
    }

    @Override
    public ArrayBlockingQueue<?> findTaskQueueById(Long id) {
        if (taskQueuePool.containsKey(id)) {
            return taskQueuePool.get(id);
        }
        return null;
    }

    @Override
    public void addTaskQueue(Long id, Integer maxTaskCount) {
        taskQueuePool.put(id, new ArrayBlockingQueue<>(maxTaskCount));
    }

    @Override
    public void addTaskQueue(Long id) {
        taskQueuePool.put(id, new ArrayBlockingQueue<>(taskQueueProperties.getDefaultMaxQueueSize()));
    }

    @Override
    public void removeTaskQueue(Long id) {
        taskQueuePool.remove(id);
    }
}
