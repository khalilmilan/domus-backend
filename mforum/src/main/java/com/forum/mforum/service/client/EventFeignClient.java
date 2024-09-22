package com.forum.mforum.service.client;

import com.forum.mforum.dto.EventDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="microservice-event", fallback = EventFallback.class)
public interface EventFeignClient {
    @GetMapping(value = "/event/{idEvent}",consumes = "application/json")
    public ResponseEntity<EventDTO> getEvent(@PathVariable("idEvent") Long idEvent);
}
