package com.mproject.mproject.service.client;

import com.mproject.mproject.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="microservice-project-user", fallback = ProjectUserFallback.class)
public interface ProjectUserFeignClient {
    @GetMapping(value = "/project-user/by_project/{idProject}",consumes = "application/json")
    ResponseEntity<List<UserDTO>> getProjectUserByProject(@PathVariable("idProject") Long idProject);

}
