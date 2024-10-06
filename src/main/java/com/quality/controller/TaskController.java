package com.quality.controller;

import com.quality.model.Response;
import com.quality.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "任务管理")
@RestController
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Operation(summary = "获取任务状态")
    @GetMapping("/count/{slaveId}")
    public Response<?> getTaskStatus(@PathVariable Long slaveId) {
        Integer[] taskStatus = taskService.getTaskStatus(slaveId);
        Map<String, Integer> status = new HashMap<>();
        status.put("finished", taskStatus[0]);
        status.put("in_progress", taskStatus[1]);
        return Response.builder().code(200).data(status).build();
    }

    @Operation(summary = "停止当前运行的任务")
    @PostMapping("/stop/{slaveId}")
    public Response<?> stopTask(@PathVariable Long slaveId) {
        taskService.stopTask(slaveId);
        return Response.builder().code(200).message("请求停止任务成功").build();
    }
}
