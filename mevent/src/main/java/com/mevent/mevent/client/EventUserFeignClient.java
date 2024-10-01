package com.mevent.mevent.client;

import com.mevent.mevent.configurations.FeignClientSecurityConfig;
import com.mevent.mevent.dto.EventUserDTO;
import com.mevent.mevent.dto.SimpleUserDTO;
import com.mevent.mevent.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="microservice-event-user", path="/event_user",configuration = FeignClientSecurityConfig.class)
public interface EventUserFeignClient {

    @GetMapping(value = "/event/{idEvent}",consumes = "application/json")
    List<SimpleUserDTO> getAllEventsUser(@PathVariable("idEvent")Long idEvent);

    @PostMapping(value = "",consumes = "application/json")
    EventUserDTO saveEventUser(@RequestBody EventUserDTO eventUserDto);


    @DeleteMapping(value="/delete_participant/{idEvent}/{idUser}", consumes = "application/json")
    void deleteByEventAndUser(@PathVariable("idEvent") Long idEvent, @PathVariable("idUser") Long idUser);
}
