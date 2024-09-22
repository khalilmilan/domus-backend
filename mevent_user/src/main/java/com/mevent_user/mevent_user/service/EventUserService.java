package com.mevent_user.mevent_user.service;

import com.mevent_user.mevent_user.dto.EventUserDTO;
import com.mevent_user.mevent_user.dto.UserDTO;
import com.mevent_user.mevent_user.exception.EventUserException;
import com.mevent_user.mevent_user.exception.EventUserException;
import com.mevent_user.mevent_user.model.EventUser;
import org.w3c.dom.events.EventException;

import java.util.List;

public interface EventUserService {

    EventUserDTO saveEvent(EventUserDTO eventUserDTO);

    List<EventUserDTO> getALLEventUser();

    void deleteEventUser(Long idEventUser) throws EventUserException;

    EventUserDTO getEventUser(Long idEventUser) throws EventUserException;

    public void updateEventUser(Long id, EventUser event) throws EventUserException;

    public List<UserDTO> getEventUserByEvent(Long idEvent);
    public void deleteEventUserByIdEventIdUser(Long idEvent,Long idUser) throws EventUserException;
}
