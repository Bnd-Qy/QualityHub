package com.quality.controller;

import com.quality.model.ProjectModel;
import com.quality.model.Response;
import com.quality.service.ProjectService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Slf4j
@RestController
@Api(tags = "项目管理")
@RequestMapping("/project")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/query/{id}")
    public Response<?> queryProject(@PathVariable Long id) {
        ProjectModel projectModel = projectService.queryProject(id);
        return Response.builder().data(projectModel).build();
    }

    @PostMapping("/delete/{id}")
    public Response<?> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return Response.builder().code(200).message("删除成功!").build();
    }


    @GetMapping("/query/all")
    public Response<?> queryAll() {
        List<ProjectModel> allProjects = projectService.queryAllProjects();
        return Response.builder().data(allProjects).code(HttpStatus.OK.value()).build();
    }


    @PostMapping("/add")
    public Response<?> addProject(@RequestBody ProjectModel project) {
        log.info("model:{}",project);
        Long projectId = projectService.createProject(project);
        return Response.builder().code(200).data(projectId).build();
    }

    @GetMapping("/count")
    public Response<?> queryProjectCount(){
        return null;
    }
}
