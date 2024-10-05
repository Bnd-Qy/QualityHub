package com.quality.mapper;

import com.quality.model.SlaveModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SlaveMapper {
    Long createSlave(SlaveModel slaveModel);

    List<SlaveModel> findAll();

    SlaveModel findById(Long id);

    SlaveModel findByIP(String ip);

    List<SlaveModel> findOnlineNotUsedSlaveByTag(String tag);

    void updateSlave(SlaveModel slaveModel);

    List<SlaveModel> findOnLivedSlave();
}
