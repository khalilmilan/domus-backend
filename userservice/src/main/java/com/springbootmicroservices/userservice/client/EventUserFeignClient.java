package com.springbootmicroservices.userservice.client;


import com.springbootmicroservices.userservice.config.FeignClientSecurityConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="microservice-event-user", path="/event_user",configuration = FeignClientSecurityConfig.class)
public interface EventUserFeignClient {

   // @GetMapping(value = "/event/{idEvent}",consumes = "application/json")
  //  List<SimpleUserDetailsDTO> getAllEventsUser(@PathVariable("idEvent")Long idEvent);

   // @PostMapping(value = "",consumes = "application/json")
   // EventUserDTO saveEventUser(@RequestBody EventUserDTO eventUserDto);
    //@DeleteMapping(value="/delete_participant/{idEvent}/{idUser}", consumes = "application/json")
    //void deleteByEventAndUser(@PathVariable("idEvent") Long idEvent, @PathVariable("idUser") Long idUser);

    @GetMapping(value="/by_participant/{idUser}", consumes = "application/json")
    List<Long> getIdsProjectByParticipant( @PathVariable("idUser") Long idUser);

}
