package com.quality.listen;

import com.quality.event.TaskPublishEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class TaskQueueListener {
    //基于事件执行任务
    @Async("taskThreadPool")
    @EventListener
    public void runTask(TaskPublishEvent event){

    }
}
