package com.mevent_user.mevent_user.client;

import com.mevent_user.mevent_user.configurations.FeignClientSecurityConfig;
import com.mevent_user.mevent_user.dto.SimpleEventDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="microservice-event",path = "/event",configuration = FeignClientSecurityConfig.class)

public interface EventFeignClient {
    @GetMapping(value = "/simple_event/{idEvent}",consumes = "application/json")
    SimpleEventDTO getSimpleEvent(@PathVariable("idEvent") Long idEvent);
}
