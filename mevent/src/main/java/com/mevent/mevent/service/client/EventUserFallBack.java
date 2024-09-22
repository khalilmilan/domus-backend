package com.mevent.mevent.service.client;

import com.mevent.mevent.dto.EventUserDTO;
import com.mevent.mevent.dto.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class EventUserFallBack implements EventUserFeignClient{
    @Override
    public ResponseEntity<List<UserDTO>> getAllEventsUser(Long idEvent) {
        return null;
    }
    @Override
    public ResponseEntity<EventUserDTO> saveEventUser(EventUserDTO eventUserDto) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteByEventAndUser(Long idEvent, Long idUser) {
        return null;
    }
}
