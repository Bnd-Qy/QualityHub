package com.quality.mapper;

import com.quality.model.ProjectModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProjectMapper {
    ProjectModel queryProject(@Param("id") Long id);

    void deleteProject(@Param("id") Long id);

    Long addProject(ProjectModel projectModel);

    List<ProjectModel> queryAllProject();
}
