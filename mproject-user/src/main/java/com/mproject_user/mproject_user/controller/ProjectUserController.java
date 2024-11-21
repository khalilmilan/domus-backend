package com.mproject_user.mproject_user.controller;

import com.mproject_user.mproject_user.dto.ProjectDTO;
import com.mproject_user.mproject_user.dto.ProjectUserDTO;
import com.mproject_user.mproject_user.dto.SimpleUserDTO;
import com.mproject_user.mproject_user.exception.ProjectUserException;
import com.mproject_user.mproject_user.model.ProjectUser;
import com.mproject_user.mproject_user.service.ProjectUserService;
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
@RequestMapping("project-user")
@AllArgsConstructor
public class ProjectUserController {
        private ProjectUserService projectUserService;
        @GetMapping(value = "/health/readiness")
        public ResponseEntity<String> test(){
                return ResponseEntity.status(HttpStatus.OK).body("hi");
        }
        @PostMapping
        public ResponseEntity<ProjectUserDTO> saveProjectUser(@RequestBody ProjectUserDTO projectUserDto){
                ProjectUserDTO savedProjectUser = projectUserService.saveProjectUser(projectUserDto);
                return new ResponseEntity<>(savedProjectUser, HttpStatus.CREATED);
        }
        @GetMapping("")
        public ResponseEntity<?> getAllProjectUser() {
                List<ProjectUserDTO> projectsUser = projectUserService.getALLProjectUser();
                return new ResponseEntity<>(projectsUser, projectsUser.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
        }

        @GetMapping("/{idProjectUser}")
        public ResponseEntity<ProjectUserDTO> getProjectUser(@PathVariable("idProjectUser") Long idProjectUser) throws ProjectUserException {
                try {
                        return new ResponseEntity<>(projectUserService.getProjectUser(idProjectUser), HttpStatus.OK);
                } catch (ProjectUserException e) {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ProjectUserDTO());
                }
        }

        @DeleteMapping("/{idProjectUser}")
        public ResponseEntity<?> deleteById(@PathVariable("idProjectUser") Long idProjectUser) throws ProjectUserException{
                try{
                        projectUserService.deleteProjectUser(idProjectUser);
                        return new ResponseEntity<>("Successfully deleted project-user with id "+idProjectUser, HttpStatus.OK);
                }
                catch (ProjectUserException e)
                {
                        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
                }
        }

        @PutMapping("/{idProjectUser}")
        public ResponseEntity<?> updateById(@PathVariable("idProjectUser") Long idProjectUser, @RequestBody ProjectUser projectUser)
        {
                try {
                        projectUserService.updateProjectUser(idProjectUser,projectUser);
                        return new ResponseEntity<>("Updated project-user with id "+idProjectUser+"", HttpStatus.OK);
                }
                catch(ConstraintViolationException e)
                {
                        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
                }
                catch (ProjectUserException e) {
                        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
                }
        }

        @GetMapping("/by_user/{idUser}")
        public ResponseEntity<List<ProjectDTO>> getProjectUserByUser(@PathVariable("idUser") Long idUser) {
                List<ProjectDTO> projects = projectUserService.getProjectUserByUser(idUser);
                if(projects.size()>0){
                        return new ResponseEntity<>(projects,  HttpStatus.OK );

                }else{
                        return new ResponseEntity<>(new ArrayList<ProjectDTO>(), HttpStatus.NOT_FOUND);

                }
        }
        @GetMapping("/by_project/{idProject}")
        public List<SimpleUserDTO> getProjectUserByProject(@PathVariable("idProject") Long idProject) {
                List<SimpleUserDTO> membres = projectUserService.getProjectUserByProject(idProject);
                if(membres.size()>0){
                        return membres ;

                }else{
                        return new ArrayList<>();
                }
        }
        @DeleteMapping("/delete_participant/{idProject}/{idUser}")
        public void deleteByProjectAndUser(@PathVariable("idProject") Long idProject,@PathVariable("idUser") Long idUser) throws ProjectUserException{
                try{
                        projectUserService.deleteProjectUserByIdProjectIdUser(idProject,idUser);
                }
                catch (EventException e)
                {
                }
        }
        @GetMapping("/not_in/{idProject}")
        List<SimpleUserDTO> getAllPossibleUser(@PathVariable("idProject")Long idProject){
                List<SimpleUserDTO> participants = projectUserService.getPossibleUser(idProject);
                return participants;
        }
        @GetMapping("/by_participant/{idUser}")
        List<Long> getIdsProjectByParticipant(@PathVariable("idUser")Long idUser){
                return projectUserService.getProjectByParticipant(idUser);
        }


}
