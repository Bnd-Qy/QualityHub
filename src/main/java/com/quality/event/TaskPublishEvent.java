package com.quality.event;

;

import java.util.concurrent.Future;

public class TaskPublishEvent {
    /**
     * 任务执行的节点
     */
    private Long slaveId;
    /**
     * 任务Id
     */
    private Long taskId;
    /**
     * 任务描述信息
     */
    private String desc;

    /**
     * 任务对象
     */
    private Future<?> task;
}
