package com.quality.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FolderModel {
    /**
     * 文件夹ID
     */
    private Long id;
    /**
     * 文件夹名
     */
    private String name;
    /**
     * 文件夹的层次 1/2/3...
     */
    private Integer level;
    /**
     * 父级文件夹ID
     */
    private Long parentId;
}
