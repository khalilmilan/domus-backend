package com.springbootmicroservices.userservice.client;

import com.springbootmicroservices.userservice.config.FeignClientSecurityConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservice-project", path = "/project", configuration = FeignClientSecurityConfig.class)

public interface ProjectServiceClient {
    @GetMapping(value = "/count_by_user/{idUser}",consumes = "application/json")
    int getProjectCountByUser(@PathVariable("idUser") Long idUser);
}
