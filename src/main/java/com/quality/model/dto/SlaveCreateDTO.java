package com.quality.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SlaveCreateDTO implements Serializable {
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
     * Slave Password
     */
    private String password;
}
