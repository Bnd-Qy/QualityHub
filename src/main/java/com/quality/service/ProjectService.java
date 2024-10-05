package com.quality.service;

import com.quality.model.ProjectModel;

import java.util.List;

public interface ProjectService {
    Long createProject(ProjectModel project);

    void deleteProject(Long projectId);

    Long updateProject(ProjectModel project);

    ProjectModel queryProject(Long projectId);

    List<ProjectModel> queryAllProjects();
}
