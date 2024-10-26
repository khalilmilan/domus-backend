package com.mevent.mevent.controller;

import com.mevent.mevent.dto.EventDTO;
import com.mevent.mevent.exception.EventException;
import com.mevent.mevent.model.Event;
import com.mevent.mevent.service.EventService;
import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("event")
@AllArgsConstructor
public class EventController {
    private EventService eventService;
    @GetMapping(value = "/lowel")
    public String test(){
        return "hi";
    }
    @PostMapping
    public ResponseEntity<EventDTO> saveEvent(@RequestBody EventDTO eventDto){
        EventDTO savedEvent = eventService.saveEvent(eventDto);
        return new ResponseEntity<>(savedEvent, HttpStatus.CREATED);
    }
    @GetMapping("")
    public ResponseEntity<?> getAllEvents() {
        List<EventDTO> events = eventService.getALLEvent();
        return new ResponseEntity<>(events, events.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{idEvent}")
    public ResponseEntity<EventDTO> getEvent(@PathVariable("idEvent") Long idEvent) throws EventException {
        try {
            return  new ResponseEntity<>(eventService.getEvent(idEvent),HttpStatus.OK);
        } catch (EventException e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new EventDTO());
        }
    }

    @DeleteMapping("/{idEvent}")
    public ResponseEntity<?> deleteById(@PathVariable("idEvent") Long idEvent) throws EventException{
        try{
            eventService.deleteEvent(idEvent);
            return new ResponseEntity<>("Successfully deleted event with id "+idEvent, HttpStatus.OK);
        }
        catch (EventException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{idEvent}")
    public ResponseEntity<?> updateById(@PathVariable("idEvent") Long idEvent, @RequestBody Event event)
    {
        try {
            eventService.updateEvent(idEvent,event);
            return new ResponseEntity<>("Updated event with id "+idEvent+"", HttpStatus.OK);
        }
        catch(ConstraintViolationException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch (EventException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/add_participant/{idEvent}/{idUser}")
    public ResponseEntity<?> addParticiapant(@PathVariable("idEvent") Long idEvent,@PathVariable("idUser") Long idUser){
        try{
             eventService.addPartipant(idEvent,idUser);
            return new ResponseEntity<>("Successfully added participant with id "+idUser+"in event:"+idEvent, HttpStatus.OK);
        }
        catch (EventException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{idEvent}/{idUser}")
    public ResponseEntity<?> deleteByEventUser(@PathVariable("idEvent") Long idEvent,@PathVariable("idUser") Long idUser) throws EventException{
        try{
            eventService.removeParticiapant(idEvent,idUser);
            return new ResponseEntity<>("Successfully deleted particiapant with id "+idUser+"from event :"+idEvent, HttpStatus.OK);
        }
        catch (EventException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/byUser/{idUser}")
    public ResponseEntity<List<EventDTO>> getEventByUser(@PathVariable("idUser") Long idUser) throws EventException {
        List<EventDTO> events = eventService.getEventsByUser(idUser);
        return new ResponseEntity<>(events, events.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
    @GetMapping("/count_event_by_user/{idUser}")
    public int getCountEventByUser(@PathVariable("idUser") Long idUser) throws EventException {
        return eventService.getCountEventByUser(idUser);
    }
    @GetMapping("/get_event_by_participant/{idUser}")
    public List<EventDTO> getEventsByParticipant(@PathVariable("idUser") Long idUser){
        return eventService.getEventByParticipant(idUser);
    }
}
