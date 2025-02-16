package com.mproject.mproject.service;

import com.mproject.mproject.client.*;
import com.mproject.mproject.dto.*;
import com.mproject.mproject.exception.ProjectException;
import com.mproject.mproject.mapper.ProjectMapper;
import com.mproject.mproject.model.Project;
import com.mproject.mproject.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;



import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProjectServiceImple implements ProjectService{
    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectServiceImple.class);

    private ProjectRepository projectRepository;
    private UserServiceClient userFeignClient;
    private TicketFeignClient ticketFeignClient;
    private ProjectUserFeignClient projectUserFeignClient;

    private NotificationFeignClient notificationFeignClient;
    private UserDeviceFeignClient userDeviceFeignClient;
    @Override
    public ProjectDTO saveProject(ProjectDTO projectDto) {
        Project project = ProjectMapper.mapToProject(projectDto);
        project.setCreatedAt(LocalDateTime.now());
        Project savedProject = projectRepository.save(project);
        ProjectDTO savedProjectDto = ProjectMapper.mapToProjectDto(savedProject);
        return savedProjectDto;
    }

    @Override
    public List<ProjectDTO> getALLProject() {
        List<Project> projects = projectRepository.findAll();
        List<ProjectDTO> projectsDto= new ArrayList<>();
        if (projects.size() > 0) {
            for (Project project : projects) {
                ProjectDTO dto = ProjectMapper.mapToProjectDto(project);
                SimpleUserDTO userDto = userFeignClient.getSimpleUser(dto.getIdUser());
                dto.setUser(userDto);
                projectsDto.add(dto);
            }
            return projectsDto;
        }else {
            return new ArrayList<ProjectDTO>();
        }
    }

    @Override
    public void deleteProject(Long idProject) throws ProjectException {
        Optional<Project> projectOptional = projectRepository.findById(idProject);
        if (!projectOptional.isPresent()) {
            throw new ProjectException(ProjectException.NotFoundException(idProject));
        } else {
            projectRepository.deleteById(idProject);
        }
    }

    @Override
    public ProjectDTO getProject(Long idProject) throws ProjectException {
        Optional<Project> projectOptional = projectRepository.findById(idProject);
        if (!projectOptional.isPresent()) {
            throw new ProjectException(ProjectException.NotFoundException(idProject));
        }else {
            ProjectDTO dto = ProjectMapper.mapToProjectDto(projectOptional.get());
            SimpleUserDTO userDto = userFeignClient.getSimpleUser(dto.getIdUser());
            dto.setUser(userDto);
            List<TicketDTO> tickets = ticketFeignClient.getTicketByProject(dto.getIdProject());
            dto.setTickets(tickets);
            List<SimpleUserDTO> membres = projectUserFeignClient.getProjectUserByProject(dto.getIdProject());
            dto.setMembres(membres);
            return dto;
        }
    }

    @Override
    public void updateProject(Long id, Project project) throws ProjectException {
        Optional<Project> projectWithId = projectRepository.findById(id);
        if(projectWithId.isPresent())
        {
            Project projectToUpdate=projectWithId.get();
            projectToUpdate.setTitle(project.getTitle());
            projectToUpdate.setDescription(project.getDescription());
            projectToUpdate.setStartDate(project.getStartDate());
            projectToUpdate.setEndDate(project.getEndDate());
            projectToUpdate.setStatus(project.getStatus());
            projectRepository.save(projectToUpdate);
        }
        else
        {
            throw new ProjectException(ProjectException.NotFoundException(id));
        }
    }

    @Override
    public List<ProjectDTO> getProjectByUser(Long idUser) {
        List<Project> projects = projectRepository.findProjectsByIdUser(idUser);
        List<ProjectDTO> projectsDto= new ArrayList<>();
        if (projects.size() > 0) {
            for (Project project : projects) {
                ProjectDTO dto = ProjectMapper.mapToProjectDto(project);
                SimpleUserDTO userDto = userFeignClient.getSimpleUser(dto.getIdUser());
                dto.setUser(userDto);
                projectsDto.add(dto);
            }
            return projectsDto;
        }else {
            return new ArrayList<ProjectDTO>();
        }
    }

    @Override
    public void addPartipant(Long idProject, Long idUser) throws ProjectException{
        Optional<Project> projectWithId = projectRepository.findById(idProject);
        if(projectWithId.isPresent())
        {
            ProjectUserDTO projectUserDto = new ProjectUserDTO(
                    idProject,
                    idUser,
                    1
            );
            ProjectUserDTO dto = projectUserFeignClient.saveProjectUser(projectUserDto);
            List<UserDeviceDTO> devices = userDeviceFeignClient.getDevice(idUser);
            for (UserDeviceDTO device:devices){
                NotificationDTO notification = new NotificationDTO();
                notification.setTitle("📝 New Project Assigned");
                notification.setDescription("You are added to new project "+projectWithId.get().getTitle());
                notification.setType(7);
                notification.setToken(device.getToken());
                notification.setBadgeCount(1);
                notification.setIdReciver(idUser);
                notification.setImageUrl("https://images.unsplash.com/photo-1517423440428-a5a00ad493e8");
                notification.setDate(new Date());
                notificationFeignClient.saveNotification(notification);
            }
        }
        else
        {
            throw new ProjectException(ProjectException.NotFoundException(idProject));
        }
    }

    @Override
    public void removeParticiapant(Long idProject, Long idUser) throws ProjectException{
        Optional<Project> projecttWithId = projectRepository.findById(idProject);
        if(projecttWithId.isPresent())
        {
            projectUserFeignClient.deleteByProjectAndUser(idProject,idUser);
            List<UserDeviceDTO> devices = userDeviceFeignClient.getDevice(idUser);
            for (UserDeviceDTO device:devices){
                NotificationDTO notification = new NotificationDTO();
                notification.setTitle("🚫 Excluded from Project");
                notification.setDescription("You are excluded from project "+projecttWithId.get().getTitle());
                notification.setType(7);
                notification.setToken(device.getToken());
                notification.setBadgeCount(1);
                notification.setIdReciver(idUser);
                notification.setImageUrl("https://images.unsplash.com/photo-1517423440428-a5a00ad493e8");
                notification.setDate(new Date());
                notificationFeignClient.saveNotification(notification);
            }
        }
        else
        {
            throw new ProjectException(ProjectException.NotFoundException(idProject));
        }
    }

    @Override
    public int getCountProjectByUser(Long idUser) {
        return projectRepository.findProjectsByIdUser(idUser).size();
    }

    @Override
    public List<ProjectDTO> getProjectByParticipant(Long idUser) {
        List<Long> idsProject = projectUserFeignClient.getIdsProjectByParticipant(idUser);
        List<Project> projects = projectRepository.findAllById(idsProject);
        List<ProjectDTO> projectsDto= new ArrayList<>();
        if (projects.size() > 0) {
            for (Project project : projects) {
                ProjectDTO dto = ProjectMapper.mapToProjectDto(project);
                SimpleUserDTO userDto = userFeignClient.getSimpleUser(dto.getIdUser());
                dto.setUser(userDto);
                projectsDto.add(dto);
            }
            return projectsDto;
        }else {
            return new ArrayList<ProjectDTO>();
        }
    }
}
