package com.mproject.mproject.client;

import com.mproject.mproject.configurations.FeignClientSecurityConfig;
import com.mproject.mproject.dto.SimpleUserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="microservice-project-user", path = "/project-user",configuration = FeignClientSecurityConfig.class)
public interface ProjectUserFeignClient {
    @GetMapping(value = "/by_project/{idProject}",consumes = "application/json")
    List<SimpleUserDTO> getProjectUserByProject(@PathVariable("idProject") Long idProject);

}
