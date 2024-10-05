package com.quality.controller;

import com.quality.model.Response;
import com.quality.model.dto.SlaveCreateDTO;
import com.quality.model.vo.SlaveInfoVo;
import com.quality.service.SlaveManageService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Api(tags = "节点管理")
@RestController
@RequestMapping("/slave")
public class SlaveController {
    private final SlaveManageService slaveManageService;

    public SlaveController(SlaveManageService slaveManageService) {
        this.slaveManageService = slaveManageService;
    }

    @Operation(summary = "添加节点", description = "添加节点，需要对应的节点地址、登录账户及密码")
    @PostMapping("/add")
    public Response<?> addSlave(SlaveCreateDTO slaveCreateDTO) {
        Long slaveId = slaveManageService.addSlave(slaveCreateDTO);
        Map<String, Long> data = new HashMap<>();
        data.put("slaveId", slaveId);
        return Response.builder().code(200).message("创建成功").data(data).build();
    }

    @Operation(summary = "连接节点")
    @PostMapping("/connect")
    public Response<?> connectSlave(@RequestParam Long slaveId) {
        slaveManageService.connectSlave(slaveId);
        return Response.builder().code(200).message("连接成功!").build();
    }

    @Operation(summary = "断开连接节点")
    @PostMapping("/disconnect")
    public Response<?> disconnectSlave(@RequestParam Long slaveId, @RequestParam(defaultValue = "false") Boolean force) {
        slaveManageService.disconnectSlave(slaveId, force);
        return Response.builder().code(200).message("断开连接成功!").build();
    }

    @Operation(summary = "在目标节点上执行命令")
    @PostMapping("/exec")
    public Response<?> execCommand(String ip, String tag, String command) throws Exception {
        String execResult = slaveManageService.execCommand(ip, tag, command);
        return Response.builder().code(200).message("执行成功!").data(execResult).build();
    }

    @Operation(summary = "查询所有节点")
    @GetMapping("/get/all")
    public Response<?> queryAllSlave() {
        List<SlaveInfoVo> allSlave = slaveManageService.findAllSlave();
        return Response.builder().code(200).data(allSlave).build();
    }

    @GetMapping("/ttl/{slaveId}")
    public Response<?> ttl(@PathVariable Long slaveId) {
        return null;
    }
}
