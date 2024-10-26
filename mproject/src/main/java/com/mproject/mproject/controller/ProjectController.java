package com.mproject.mproject.controller;

import com.mproject.mproject.dto.ProjectDTO;
import com.mproject.mproject.exception.ProjectException;
import com.mproject.mproject.model.Project;
import com.mproject.mproject.service.ProjectService;
import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.events.EventException;

import java.util.ArrayList;
import java.util.List;
@RestController
@CrossOrigin("*")
@RequestMapping("project")
@AllArgsConstructor
public class ProjectController {
    private ProjectService projectService;
    @GetMapping(value = "/lowel")
    public String test(){
        return "hi";
    }
    @PostMapping
    public ResponseEntity<ProjectDTO> saveProject(@RequestBody ProjectDTO projectDto){
        ProjectDTO savedProject = projectService.saveProject(projectDto);
        return new ResponseEntity<>(savedProject, HttpStatus.CREATED);
    }
    @GetMapping("")
    public ResponseEntity<?> getAllProject() {
        List<ProjectDTO> projects = projectService.getALLProject();
        return new ResponseEntity<>(projects, projects.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{idProject}")
    public ProjectDTO getProject(@PathVariable("idProject") Long idProject) throws ProjectException {
        try {
            return projectService.getProject(idProject);
        } catch (ProjectException e) {
            return new ProjectDTO();
        }
    }
    @DeleteMapping("/{idProject}")
    public ResponseEntity<?> deleteById(@PathVariable("idProject") Long idProject) throws ProjectException{
        try{
            projectService.deleteProject(idProject);
            return new ResponseEntity<>("Successfully deleted project with id "+idProject, HttpStatus.OK);
        }
        catch (ProjectException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{idProject}")
    public ResponseEntity<?> updateById(@PathVariable("idProject") Long idProject, @RequestBody Project project)
    {
        try {
            projectService.updateProject(idProject,project);
            return new ResponseEntity<>("Updated project with id "+idProject+"", HttpStatus.OK);
        }
        catch(ConstraintViolationException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch (ProjectException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/by_user/{idUser}")
    public List<ProjectDTO> getProjectByUser(@PathVariable("idUser") Long idUser) {
        List<ProjectDTO> projects = projectService.getProjectByUser(idUser);
        if(projects.size()>0){
            return projects;
        }else{
            return new ArrayList<>();

        }
    }
    @PostMapping("/add_membre/{idProject}/{idUser}")
    public ResponseEntity<?> addParticiapant(@PathVariable("idProject") Long idProject,@PathVariable("idUser") Long idUser){
        try{
            projectService.addPartipant(idProject,idUser);
            return new ResponseEntity<>("Successfully added participant with id "+idUser+"in project:"+idProject, HttpStatus.OK);
        }
        catch (ProjectException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete_memebre/{idProject}/{idUser}")
    public ResponseEntity<?> deleteByEventUser(@PathVariable("idProject") Long idProject,@PathVariable("idUser") Long idUser) throws EventException{
        try{
            projectService.removeParticiapant(idProject,idUser);
            return new ResponseEntity<>("Successfully deleted particiapant with id "+idUser+"from project :"+idProject, HttpStatus.OK);
        }
        catch (ProjectException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/count_by_user/{idUser}")
    public int getProjectCountByUser(@PathVariable("idUser") Long idUser) {
        return projectService.getCountProjectByUser(idUser);
    }
    @GetMapping("/by_participant/{idUser}")
    public List<ProjectDTO> getProjectByParticipant(@PathVariable("idUser") Long idUser) {
        return projectService.getProjectByParticipant(idUser);
    }
}
