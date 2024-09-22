package com.mticket.mticket.controller;

import com.mticket.mticket.dto.TicketDTO;
import com.mticket.mticket.exception.TicketException;
import com.mticket.mticket.model.Ticket;
import com.mticket.mticket.service.TicketService;
import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("ticket")
@AllArgsConstructor
public class TicketController {
    private TicketService ticketService;
    @GetMapping(value = "/lowel")
    public String test(){
        return "hi";
    }
    @PostMapping
    public ResponseEntity<TicketDTO> saveTicket(@RequestBody TicketDTO ticketDto){
        TicketDTO savedTicket = ticketService.saveTicket(ticketDto);
        return new ResponseEntity<>(savedTicket, HttpStatus.CREATED);
    }
    @GetMapping("")
    public ResponseEntity<?> getAllTicket() {
        List<TicketDTO> tickets = ticketService.getALLTicket();
        return new ResponseEntity<>(tickets, tickets.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{idTicket}")
    public ResponseEntity<TicketDTO> getTicket(@PathVariable("idTicket") Long idTicket) throws TicketException {
        try {
            return new ResponseEntity<>(ticketService.getTicket(idTicket), HttpStatus.OK);
        } catch (TicketException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new TicketDTO());
        }
    }

    @DeleteMapping("/{idTicket}")
    public ResponseEntity<?> deleteById(@PathVariable("idTicket") Long idTicket) throws TicketException{
        try{
            ticketService.deleteTicket(idTicket);
            return new ResponseEntity<>("Successfully deleted ticket with id "+idTicket, HttpStatus.OK);
        }
        catch (TicketException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{idTicket}")
    public ResponseEntity<?> updateById(@PathVariable("idTicket") Long idTicket, @RequestBody Ticket ticket)
    {
        try {
            ticketService.updateTicket(idTicket,ticket);
            return new ResponseEntity<>("Updated ticket with id "+idTicket+"", HttpStatus.OK);
        }
        catch(ConstraintViolationException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch (TicketException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/by_user/{idUser}")
    public ResponseEntity<List<TicketDTO>> getTicketByUser(@PathVariable("idUser") Long idUser) {
        List<TicketDTO> tickets = ticketService.getTicketByUser(idUser);
        if(tickets.size()>0){
            return new ResponseEntity<>(tickets,  HttpStatus.OK );

        }else{
            return new ResponseEntity<>(new ArrayList<TicketDTO>(), HttpStatus.NOT_FOUND);

        }
    }
    @GetMapping("/by_affected/{idAffectedTo}")
    public ResponseEntity<List<TicketDTO>> getTicketByAffectedTo(@PathVariable("idAffectedTo") Long idAffectedTo) {
        List<TicketDTO> tickets = ticketService.getTicketByAffectedTo(idAffectedTo);
        if(tickets.size()>0){
            return new ResponseEntity<>(tickets,  HttpStatus.OK );

        }else{
            return new ResponseEntity<>(new ArrayList<TicketDTO>(), HttpStatus.NOT_FOUND);

        }
    }
    @GetMapping("/by_project/{idProject}")
    public ResponseEntity<List<TicketDTO>> getTicketByProject(@PathVariable("idProject") Long idProject) {
        List<TicketDTO> tickets = ticketService.getTicketByProject(idProject);
        if(tickets.size()>0){
            return new ResponseEntity<>(tickets,  HttpStatus.OK );

        }else{
            return new ResponseEntity<>(new ArrayList<TicketDTO>(), HttpStatus.NOT_FOUND);

        }
    }
}
