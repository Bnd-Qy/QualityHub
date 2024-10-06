package com.quality.service.impl;

import com.quality.service.TaskQueueManage;
import com.quality.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class TaskServiceImpl implements TaskService {
    private final TaskQueueManage taskQueueManage;

    public TaskServiceImpl(TaskQueueManage taskQueueManage) {
        this.taskQueueManage = taskQueueManage;
    }

    @Override
    public Integer[] getTaskStatus(Long id) {
        //扫描队列中的任务
        AtomicInteger finishedTask = new AtomicInteger();
        AtomicInteger inProgressTask = new AtomicInteger();
        ArrayBlockingQueue<Future<String>> taskQueue = taskQueueManage.findTaskQueueById(id);
        if (ObjectUtils.isEmpty(taskQueue)) {
            return new Integer[]{0, 0};
        }
        taskQueue.forEach(task -> {
            if (task.isDone()) {
                finishedTask.getAndIncrement();
            } else {
                inProgressTask.getAndIncrement();
            }
        });
        return new Integer[]{finishedTask.get(), inProgressTask.get()};
    }

    @Override
    public void stopTask(Long id) {
        //获取任务队列尾端的任务
        ArrayBlockingQueue<Future<String>> taskQueue = taskQueueManage.findTaskQueueById(id);
        if (!CollectionUtils.isEmpty(taskQueue)){
            taskQueue.forEach(task -> task.cancel(true));
            //从队列管理器中清除该任务队列
            taskQueueManage.removeTaskQueue(id);
        }
    }
}
