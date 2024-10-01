package com.mticket.mticket.client;

import com.mticket.mticket.configurations.FeignClientSecurityConfig;
import com.mticket.mticket.dto.ProjectDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="microservice-project", path = "/project", configuration = FeignClientSecurityConfig.class)
public interface ProjectFeignClient {
    @GetMapping(value = "/{idProject}",consumes = "application/json")
    ProjectDTO getProject(@PathVariable("idProject") Long idProject);
}
