package com.quality.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SlaveInfoVo implements Serializable {
    /**
     * Slave id
     */
    private Long id;

    private String ip;
    /**
     * Slave Connect port
     */
    private Short port;
    /**
     * Slave Tag
     */
    private String tag;
    /**
     * Slave Username
     */
    private String username;
    /**
     * Slave is online?
     */
    private Boolean isOnline;
    /**
     * Slave is used?
     */
    private Boolean isUsed;
    /**
     * create user
     */
    private String createUser;
    /**
     * create time
     */
    private Date createTime;
    /**
     * update time
     */
    private Date updateTime;
}

