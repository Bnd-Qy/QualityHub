package com.quality.schedule;

import com.quality.model.vo.SlaveInfoVo;
import com.quality.service.SlaveManageService;
import com.quality.utils.ShellUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class SlaveKeepLiveSchedule {
    private SlaveManageService slaveManageService;

    private ShellUtil shellUtil;

    public SlaveKeepLiveSchedule(SlaveManageService slaveManageService, ShellUtil shellUtil) {
        this.slaveManageService = slaveManageService;
        this.shellUtil = shellUtil;
    }

    @Scheduled(cron = "*/10 * * * * *")
    public void keepOnLive() {
        List<SlaveInfoVo> onLivedSlave = slaveManageService.findOnLivedSlave();
        //获取回话
        List<String> livedIPList = onLivedSlave.stream().map(SlaveInfoVo::getIp).collect(Collectors.toList());
        for (String livedIP : livedIPList) {
            try {
                String result = shellUtil.execCmd(livedIP, "echo 'keep live'");
                log.info("keep live:{}",result);
            } catch (Exception e) {
                log.error(e.getMessage(),e);
                log.warn("slave is down:{}",livedIP);
                slaveManageService.disconnectSlaveByIP(livedIP,true);
            }
        }
    }
}
