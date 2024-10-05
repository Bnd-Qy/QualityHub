package com.quality.service;

import com.quality.model.FolderModel;

public interface FileManagerService {
    Long createFolder(FolderModel folder);

    void deleteFolder(Long folderId);
}
