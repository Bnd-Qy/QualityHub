package com.quality.service;

public interface TaskService {
    Integer[] getTaskStatus(Long id);
    void stopTask(Long id);
}
