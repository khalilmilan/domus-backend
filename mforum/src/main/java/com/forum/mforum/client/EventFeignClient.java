package com.forum.mforum.client;

import com.forum.mforum.configurations.EventFeignClientConfig;

import com.forum.mforum.dto.EventDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="microservice-event",path = "/event",configuration = EventFeignClientConfig.class)
public interface EventFeignClient {
    @GetMapping(value = "/{idEvent}",consumes = "application/json")
    EventDTO getEvent(@PathVariable("idEvent") Long idEvent);
}
