package com.mproject_user.mproject_user.service.client;

import com.mproject_user.mproject_user.dto.ProjectDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="microservice-project", fallback = ProjectFallback.class)
public interface ProjectFeignClient {
    @GetMapping(value = "/project/{idProject}",consumes = "application/json")
    ResponseEntity<ProjectDTO> getProject(@PathVariable("idProject") Long idProject);
}
