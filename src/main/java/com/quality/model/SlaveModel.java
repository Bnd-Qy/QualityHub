package com.quality.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SlaveModel implements Serializable {
    /**
     * Slave ID
     */
    private Long id;
    /**
     * Slave IP
     */
    private String ip;
    /**
     * Slave Username
     */
    private String username;
    /**
     * Slave Password
     */
    private String password;
    /**
     * Slave Connect port
     */
    private Short port;
    /**
     * Slave Tag
     */
    private String tag;
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
    private Long createUser;
    /**
     * create time
     */
    private Date createTime;
    /**
     * update time
     */
    private Date updateTime;
}
