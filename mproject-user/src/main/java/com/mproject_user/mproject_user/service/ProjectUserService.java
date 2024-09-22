package com.mproject_user.mproject_user.service;

import com.mproject_user.mproject_user.dto.ProjectDTO;
import com.mproject_user.mproject_user.dto.ProjectUserDTO;
import com.mproject_user.mproject_user.dto.UserDTO;
import com.mproject_user.mproject_user.exception.ProjectUserException;
import com.mproject_user.mproject_user.model.ProjectUser;

import java.util.List;

public interface ProjectUserService {
    ProjectUserDTO saveProjectUser(ProjectUserDTO projectUserDto);

    List<ProjectUserDTO> getALLProjectUser();

    void deleteProjectUser(Long idProjectUser) throws ProjectUserException;

    ProjectUserDTO getProjectUser(Long idProjectUser) throws ProjectUserException;

    void updateProjectUser(Long id, ProjectUser projectUser) throws ProjectUserException;
    List<UserDTO> getProjectUserByProject(Long idProject);
    List<ProjectDTO> getProjectUserByUser(Long idUser);
}
