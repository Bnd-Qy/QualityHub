package com.quality.mapper;

import com.quality.model.FolderModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileManagerMapper {
    FolderModel queryFolderByName(String name, Integer level);

    Long createFolder(FolderModel folder);

    void deleteFolder(Long folderId);
}
