package com.springbootmicroservices.userservice.client;


import com.springbootmicroservices.userservice.config.FeignClientSecurityConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="microservice-project-user", path = "/project-user",configuration = FeignClientSecurityConfig.class)
public interface ProjectUserFeignClient {
    //@GetMapping(value = "/by_project/{idProject}",consumes = "application/json")
   // List<SimpleUserDTO> getProjectUserByProject(@PathVariable("idProject") Long idProject);

    //@PostMapping(value = "",consumes = "application/json")
   // ProjectUserDTO saveProjectUser(@RequestBody ProjectUserDTO projectUserDto);

   // @DeleteMapping(value="/delete_participant/{idProject}/{idUser}", consumes = "application/json")
   // void deleteByProjectAndUser(@PathVariable("idProject") Long idProject, @PathVariable("idUser") Long idUser);

    @GetMapping("/by_participant/{idUser}")
    List<Long> getIdsProjectByParticipant(@PathVariable("idUser")Long idUser);

}
