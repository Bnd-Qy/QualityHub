package com.quality.service.impl;

import com.quality.model.FolderModel;
import com.quality.service.FileManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FileManagerServiceImpl implements FileManagerService {
    @Override
    public Long createFolder(FolderModel folder) {
        //1.查询文件夹是否存在
        //2.创建文件夹
        return null;
    }

    @Override
    public void deleteFolder(Long folderId) {
        //1.判断文件夹是否为空
        //2.删除文件夹
    }
}
