package com.mproject_user.mproject_user.client;

import com.mproject_user.mproject_user.configurations.FeignClientSecurityConfig;
import com.mproject_user.mproject_user.dto.ProjectDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="microservice-project", path = "/project",configuration = FeignClientSecurityConfig.class)
public interface ProjectFeignClient {
    @GetMapping(value = "/{idProject}",consumes = "application/json")
    ProjectDTO getProject(@PathVariable("idProject") Long idProject);
}
