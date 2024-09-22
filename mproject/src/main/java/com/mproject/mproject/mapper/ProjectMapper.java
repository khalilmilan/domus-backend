package com.mproject.mproject.mapper;

import com.mproject.mproject.dto.ProjectDTO;
import com.mproject.mproject.dto.UserDTO;
import com.mproject.mproject.model.Project;

import java.util.ArrayList;

public class ProjectMapper {
    public static ProjectDTO mapToProjectDto(Project project){
        ProjectDTO projectDto = new ProjectDTO(
                project.getIdProject(),
                project.getTitle(),
                project.getDescription(),
                project.getStatus(),
                project.getIdUser(),
                new UserDTO(),
                new ArrayList<>(),
                new ArrayList<>()
        );
        return projectDto;
    }


    public static Project mapToProject(ProjectDTO projectDto){
        Project project = new Project(
                projectDto.getIdProject(),
                projectDto.getTitle(),
                projectDto.getDescription(),
                projectDto.getStatus(),
                projectDto.getIdUser()
        );
        return project;
    }
}
