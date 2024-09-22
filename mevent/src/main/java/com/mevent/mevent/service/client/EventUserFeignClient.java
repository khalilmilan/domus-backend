package com.mevent.mevent.service.client;

import com.mevent.mevent.dto.EventUserDTO;
import com.mevent.mevent.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="microservice-event-user", fallback = EventUserFallBack.class)
public interface EventUserFeignClient {

    @GetMapping(value = "/event_user/event/{idEvent}",consumes = "application/json")
    public ResponseEntity<List<UserDTO>> getAllEventsUser(@PathVariable("idEvent")Long idEvent);

    @PostMapping(value = "/event_user",consumes = "application/json")
    public ResponseEntity<EventUserDTO> saveEventUser(@RequestBody EventUserDTO eventUserDto);


    @DeleteMapping(value="/event_user/delete_participant/{idEvent}/{idUser}", consumes = "application/json")
    public ResponseEntity<?> deleteByEventAndUser(@PathVariable("idEvent") Long idEvent, @PathVariable("idUser") Long idUser);
}
