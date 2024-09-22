package com.mproject_user.mproject_user.mapper;

import com.mproject_user.mproject_user.dto.ProjectUserDTO;
import com.mproject_user.mproject_user.model.ProjectUser;

public class ProjectUserMapper {
    public static ProjectUserDTO mapToProjectUserDto(ProjectUser projectUser){
        ProjectUserDTO projectUserDTO = new ProjectUserDTO(
                projectUser.getIdProjectUser(),
                projectUser.getStatus(),
                projectUser.getIdProject(),
                projectUser.getIdUser()
        );
        return projectUserDTO;
    }

    public static ProjectUser mapToProjectUser(ProjectUserDTO projectDto){
        ProjectUser projectUser = new ProjectUser(
                projectDto.getIdProjectUser(),
                projectDto.getStatus(),
                projectDto.getIdProject(),
                projectDto.getIdUser()
        );
        return projectUser;
    }
}
