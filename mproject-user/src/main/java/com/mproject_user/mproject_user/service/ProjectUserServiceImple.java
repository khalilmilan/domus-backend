package com.mproject_user.mproject_user.service;

import com.mproject_user.mproject_user.client.UserServiceClient;
import com.mproject_user.mproject_user.dto.ProjectDTO;
import com.mproject_user.mproject_user.dto.ProjectUserDTO;
import com.mproject_user.mproject_user.dto.SimpleUserDTO;
import com.mproject_user.mproject_user.exception.ProjectUserException;
import com.mproject_user.mproject_user.mapper.ProjectUserMapper;
import com.mproject_user.mproject_user.model.ProjectUser;
import com.mproject_user.mproject_user.repository.ProjectUserRepository;
import com.mproject_user.mproject_user.client.ProjectFeignClient;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class ProjectUserServiceImple implements ProjectUserService{
    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectUserServiceImple.class);
    private ProjectUserRepository projectUserRepository;
    private UserServiceClient userFeignClient;
    private ProjectFeignClient projectFeignClient;
    @Override
    public ProjectUserDTO saveProjectUser(ProjectUserDTO projectUserDto) {
        ProjectUser projectUser = ProjectUserMapper.mapToProjectUser(projectUserDto);
        ProjectUser savedProjectUser = projectUserRepository.save(projectUser);
        ProjectUserDTO ProjectUserDto = ProjectUserMapper.mapToProjectUserDto(savedProjectUser);
        return ProjectUserDto;
    }

    @Override
    public List<ProjectUserDTO> getALLProjectUser() {
        List<ProjectUser> projectsUser = projectUserRepository.findAll();
        List<ProjectUserDTO> projectsUserDto= new ArrayList<>();
        if (projectsUser.size() > 0) {
            for (ProjectUser projectUser : projectsUser) {
                ProjectUserDTO dto = ProjectUserMapper.mapToProjectUserDto(projectUser);
                projectsUserDto.add(dto);
            }
            return projectsUserDto;
        }else {
            return new ArrayList<ProjectUserDTO>();
        }
    }

    @Override
    public void deleteProjectUser(Long idProjectUser) throws ProjectUserException {
        Optional<ProjectUser> projectUserOptional = projectUserRepository.findById(idProjectUser);
        if (!projectUserOptional.isPresent()) {
            throw new ProjectUserException(ProjectUserException.NotFoundException(idProjectUser));
        } else {
            projectUserRepository.deleteById(idProjectUser);
        }
    }

    @Override
    public ProjectUserDTO getProjectUser(Long idProjectUser) throws ProjectUserException {
        Optional<ProjectUser> projectUserOptional = projectUserRepository.findById(idProjectUser);
        if (!projectUserOptional.isPresent()) {
            throw new ProjectUserException(ProjectUserException.NotFoundException(idProjectUser));
        }else {
            ProjectUserDTO dto = ProjectUserMapper.mapToProjectUserDto(projectUserOptional.get());
            return dto;
        }
    }

    @Override
    public void updateProjectUser(Long id, ProjectUser projectUser) throws ProjectUserException {
        Optional<ProjectUser> projectUserWithId = projectUserRepository.findById(id);
        if(projectUserWithId.isPresent())
        {
            ProjectUser projectUserToUpdate=projectUserWithId.get();
            projectUserToUpdate.setStatus(projectUser.getStatus());
        }
        else
        {
            throw new ProjectUserException(ProjectUserException.NotFoundException(id));
        }
    }

    @Override
    public List<SimpleUserDTO> getProjectUserByProject(Long idProject) {
        List<ProjectUser> projectsUser = projectUserRepository.findAll();
        List<SimpleUserDTO> membres= new ArrayList<>();
        if (projectsUser.size() > 0) {
            for (ProjectUser projectUser : projectsUser) {
                SimpleUserDTO user = userFeignClient.getSimpleUser(projectUser.getIdUser());
                membres.add(user);
            }
            return membres;
        }else {
            return new ArrayList<SimpleUserDTO>();
        }
    }

    @Override
    public List<ProjectDTO> getProjectUserByUser(Long idUser) {
        List<ProjectUser> projectsUser = projectUserRepository.findProjectUserByIdUser(idUser);
        List<ProjectDTO> projects= new ArrayList<>();
        if (projectsUser.size() > 0) {
            for (ProjectUser projectUser : projectsUser) {
                ProjectDTO project = projectFeignClient.getProject(projectUser.getIdProject());
                projects.add(project);
            }
            return projects;
        }else {
            return new ArrayList<ProjectDTO>();
        }
    }
}
