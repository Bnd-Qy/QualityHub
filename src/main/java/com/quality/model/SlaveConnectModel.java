package com.quality.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SlaveConnectModel {
    private String ip;
    private Integer port = 22;
    private String username;
    private String password;
}
