package com.quality.service.impl;

import com.quality.mapper.ProjectMapper;
import com.quality.model.ProjectModel;
import com.quality.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectMapper projectMapper;

    public ProjectServiceImpl(ProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
    }

    @Override
    public Long createProject(ProjectModel project) {
        project.setCreateTime(new Date());
        return projectMapper.addProject(project);
    }

    @Override
    public void deleteProject(Long projectId) {
        projectMapper.deleteProject(projectId);
    }

    @Override
    public Long updateProject(ProjectModel project) {
        return null;
    }

    @Override
    public ProjectModel queryProject(Long projectId) {
        log.info("查询project id:{}", projectId);
        return projectMapper.queryProject(projectId);
    }

    @Override
    public List<ProjectModel> queryAllProjects() {
        return projectMapper.queryAllProject();
    }
}
