package com.springbootmicroservices.userservice.client;

import com.springbootmicroservices.userservice.config.FeignClientSecurityConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservice-event", path = "/event", configuration = FeignClientSecurityConfig.class)

public interface EventServiceClient {
    @GetMapping(value = "/count_event_by_user/{idUser}",consumes = "application/json")
    int count_event_by_user(@PathVariable("idUser") Long idUser);
}
