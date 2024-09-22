package com.mproject.mproject.service;

import com.mproject.mproject.dto.ProjectDTO;
import com.mproject.mproject.exception.ProjectException;
import com.mproject.mproject.model.Project;
import org.w3c.dom.events.EventException;

import java.util.List;

public interface ProjectService {

    ProjectDTO saveProject(ProjectDTO projectDto);

    List<ProjectDTO> getALLProject();

    void deleteProject(Long idProject) throws ProjectException;

    ProjectDTO getProject(Long idProject) throws ProjectException;

    void updateProject(Long id, Project project) throws ProjectException;

    List<ProjectDTO> getProjectByUser(Long idUser);
}
