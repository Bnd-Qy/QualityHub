package com.quality.service;

import com.quality.model.dto.SlaveCreateDTO;
import com.quality.model.vo.SlaveInfoVo;

import java.util.List;

public interface SlaveManageService {

    String execCommand(String ip,String tag, String command) throws Exception;

    Long addSlave(SlaveCreateDTO slaveCreateDTO);

    List<SlaveInfoVo> findAllSlave();

    void connectSlave(Long slaveId);

    void disconnectSlave(Long slaveId,Boolean force);

    void disconnectSlaveByIP(String ip,Boolean force);

    List<SlaveInfoVo> findOnLivedSlave();
}
