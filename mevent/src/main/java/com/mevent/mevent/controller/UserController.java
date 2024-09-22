package com.mevent.mevent.controller;

import com.mevent.mevent.dto.EventDetailsDTO;
import com.mevent.mevent.exception.EventException;
import com.mevent.mevent.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("user")

public class UserController {

    private IUserService iUserService;
    public UserController(IUserService iUserService){
        this.iUserService= iUserService;
    }
    @GetMapping("/fetchEventDetails")
    public ResponseEntity<EventDetailsDTO> fetchUserDetails(Long idEvent) throws EventException {
        EventDetailsDTO eventDetailsDTO = iUserService.fetchUserDetails(idEvent);
        return ResponseEntity.status(HttpStatus.OK).body(eventDetailsDTO);
    }
}
