package com.mevent_user.mevent_user.controller;

import com.mevent_user.mevent_user.dto.EventUserDTO;
import com.mevent_user.mevent_user.dto.UserDTO;
import com.mevent_user.mevent_user.exception.EventUserException;
import com.mevent_user.mevent_user.model.EventUser;
import com.mevent_user.mevent_user.service.EventUserService;
import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.events.EventException;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("event_user")
@AllArgsConstructor
public class EventUserController {

    private EventUserService eventUserService;
    @GetMapping(value = "/lowel")
    public String test(){
        return "hi";
    }
    @PostMapping
    public ResponseEntity<EventUserDTO> saveEventUser(@RequestBody EventUserDTO eventUserDto){
        EventUserDTO savedEventUser = eventUserService.saveEvent(eventUserDto);
        return new ResponseEntity<>(savedEventUser, HttpStatus.CREATED);
    }
    @GetMapping("")
    public ResponseEntity<?> getAllEventsUser() {
        List<EventUserDTO> events = eventUserService.getALLEventUser();
        return new ResponseEntity<>(events, events.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{idEventUser}")
    public ResponseEntity<?> getEventUser(@PathVariable("idEventUser") Long idEventUser) throws EventUserException {
        try {
            return new ResponseEntity<>(eventUserService.getEventUser(idEventUser), HttpStatus.OK);
        } catch (EventUserException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{idEventUser}")
    public ResponseEntity<?> deleteById(@PathVariable("idEventUser") Long idEventUser) throws EventUserException{
        try{
            eventUserService.deleteEventUser(idEventUser);
            return new ResponseEntity<>("Successfully deleted event with id "+idEventUser, HttpStatus.OK);
        }
        catch (EventException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{idEventUser}")
    public ResponseEntity<?> updateById(@PathVariable("idEventUser") Long idEventUser, @RequestBody EventUser eventUser)
    {
        try {
            eventUserService.updateEventUser(idEventUser,eventUser);
            return new ResponseEntity<>("Updated event with id "+idEventUser+"", HttpStatus.OK);
        }
        catch(ConstraintViolationException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch (EventUserException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/event/{idEvent}")
    ResponseEntity<List<UserDTO>> getAllEventsUser(@PathVariable("idEvent")Long idEvent){
        List<UserDTO> participants = eventUserService.getEventUserByEvent(idEvent);
        return new ResponseEntity<>(participants, participants.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/delete_participant/{idEvent}/{idUser}")
    public ResponseEntity<?> deleteByEventAndUser(@PathVariable("idEvent") Long idEvent,@PathVariable("idUser") Long idUser) throws EventUserException{
        try{
            eventUserService.deleteEventUserByIdEventIdUser(idEvent,idUser);
            return new ResponseEntity<>("Successfully deleted eventUser with idEvent "+idEvent+" and iduser:"+idUser, HttpStatus.OK);
        }
        catch (EventException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
