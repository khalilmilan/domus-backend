package com.forum.mforum.service.client;

import com.forum.mforum.dto.EventDTO;
import org.springframework.http.ResponseEntity;

public class EventFallback implements EventFeignClient{
    @Override
    public ResponseEntity<EventDTO> getEvent(Long idEvent) {
        return null;
    }
}
