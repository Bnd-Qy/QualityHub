package com.quality.service.impl;

import com.jcraft.jsch.JSchException;
import com.quality.mapper.SlaveMapper;
import com.quality.model.SlaveModel;
import com.quality.model.dto.SlaveCreateDTO;
import com.quality.model.vo.SlaveInfoVo;
import com.quality.service.SlaveManageService;
import com.quality.service.UserService;
import com.quality.utils.ShellUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SlaveManageServiceImpl implements SlaveManageService {
    private final SlaveMapper slaveMapper;
    private final ShellUtil shellUtil;
    private final UserService userService;

    public SlaveManageServiceImpl(ShellUtil shellUtil, SlaveMapper slaveMapper, UserService userService) {
        this.shellUtil = shellUtil;
        this.slaveMapper = slaveMapper;
        this.userService = userService;
    }

    @Override
    public void connectSlave(Long slaveId) {
        SlaveModel slave = slaveMapper.findById(slaveId);
        if (ObjectUtils.isEmpty(slave)) {
            throw new RuntimeException("slave:" + slaveId + " not found");
        }
        String ip = slave.getIp();
        Short port = slave.getPort();
        String username = slave.getUsername();
        String password = slave.getPassword();
        //create connector
        try {
            shellUtil.init(ip, port.intValue(), username, password);
        } catch (JSchException e) {
            throw new RuntimeException(e);
        }
        slave.setIsOnline(true);
        slaveMapper.updateSlave(slave);
    }

    @Override
    public void disconnectSlave(Long slaveId, Boolean force) {
        if (force) {
            //等待当前任务运行完成
        }
        SlaveModel slave = slaveMapper.findById(slaveId);
        //清理连接
        shellUtil.close(slave.getIp());
        slave.setId(slaveId);
        //对节点进行下线
        slave.setIsUsed(false);
        slave.setIsOnline(false);
        slaveMapper.updateSlave(slave);
    }

    @Override
    public void disconnectSlaveByIP(String ip, Boolean force) {
        if (force) {
            //等待当前任务运行完成
        }
        SlaveModel slave = slaveMapper.findByIP(ip);
        shellUtil.close(slave.getIp());
        slave.setIsUsed(false);
        slave.setIsOnline(false);
        slaveMapper.updateSlave(slave);
    }

    @Override
    public List<SlaveInfoVo> findOnLivedSlave() {
        List<SlaveModel> slaveModels = slaveMapper.findOnLivedSlave();
        return slaveModels.stream().map(slaveModel -> {
            SlaveInfoVo slaveInfo = new SlaveInfoVo();
            slaveInfo.setId(slaveModel.getId());
            slaveInfo.setIp(slaveModel.getIp());
            slaveInfo.setUsername(slaveModel.getUsername());
            slaveInfo.setPort(slaveModel.getPort());
            slaveInfo.setTag(slaveModel.getTag());
            slaveInfo.setIsUsed(slaveModel.getIsUsed());
            slaveInfo.setIsOnline(slaveModel.getIsOnline());
            slaveInfo.setCreateTime(slaveModel.getCreateTime());
            slaveInfo.setUpdateTime(slaveModel.getUpdateTime());
            slaveInfo.setCreateUser("未定义");
            return slaveInfo;
        }).collect(Collectors.toList());
    }

    @Override
    public String execCommand(String ip, String tag, String command) throws Exception {
        //如果指定ip则优先使用ip
        if (!StringUtils.hasText(ip)) {
            if (!StringUtils.hasText(tag)) {
                return "ip and tag is null,Execute command failed";
            }
            List<SlaveModel> slaveList = slaveMapper.findOnlineNotUsedSlaveByTag(tag);
            if (CollectionUtils.isEmpty(slaveList)) {
                return "tag:[" + tag + "] not have online slave";
            }
            SlaveModel slave = slaveList.get(0);
            return shellUtil.execCmd(slave.getIp(), command);
        }
        return shellUtil.execCmd(ip, command);
    }

    @Override
    public Long addSlave(SlaveCreateDTO slaveCreateDTO) {
        SlaveModel slaveModel = new SlaveModel();
        BeanUtils.copyProperties(slaveCreateDTO, slaveModel);
        //TODO 从上下文获取当前用户的ID
        slaveModel.setCreateUser(1L);
        slaveModel.setCreateTime(new Date());
        slaveModel.setUpdateTime(new Date());
        return slaveMapper.createSlave(slaveModel);
    }

    @Override
    public List<SlaveInfoVo> findAllSlave() {
        List<SlaveModel> slaveModels = slaveMapper.findAll();
        Map<Long, String> ownIdNameMapping = new HashMap<>();
        List<Long> slaveOwnIds = slaveModels.stream().map(SlaveModel::getCreateUser).collect(Collectors.toList());
        List<String> usernames = userService.findUsernames(slaveOwnIds);
        //TODO
        return slaveModels.stream().map(slaveModel -> {
            SlaveInfoVo slaveInfo = new SlaveInfoVo();
            slaveInfo.setId(slaveModel.getId());
            slaveInfo.setIp(slaveModel.getIp());
            slaveInfo.setUsername(slaveModel.getUsername());
            slaveInfo.setPort(slaveModel.getPort());
            slaveInfo.setTag(slaveModel.getTag());
            slaveInfo.setIsUsed(slaveModel.getIsUsed());
            slaveInfo.setIsOnline(slaveModel.getIsOnline());
            slaveInfo.setCreateTime(slaveModel.getCreateTime());
            slaveInfo.setUpdateTime(slaveModel.getUpdateTime());
            slaveInfo.setCreateUser("未定义");
            return slaveInfo;
        }).collect(Collectors.toList());
    }
}
