package com.quality.model;

import lombok.Data;

import java.util.Date;

@Data
public class ProjectModel {
    /**
     * 项目ID
     */
    private Long id;
    /**
     * 项目名称
     */
    private String name;
    /**
     * 项目描述信息
     */
    private String desc;
    /**
     * 创建时间
     */
    private Date createTime;
}
