package com.mevent_user.mevent_user.service;

import com.mevent_user.mevent_user.dto.EventUserDTO;
import com.mevent_user.mevent_user.dto.SimpleUserDTO;
import com.mevent_user.mevent_user.dto.SimpleUserDetailsDTO;
import com.mevent_user.mevent_user.exception.EventUserException;
import com.mevent_user.mevent_user.exception.EventUserException;
import com.mevent_user.mevent_user.model.EventUser;
import org.aspectj.weaver.Lint;
import org.w3c.dom.events.EventException;

import java.util.List;

public interface EventUserService {

    EventUserDTO saveEvent(EventUserDTO eventUserDTO);

    List<EventUserDTO> getALLEventUser();

    void deleteEventUser(Long idEventUser) throws EventUserException;

    EventUserDTO getEventUser(Long idEventUser) throws EventUserException;

     void updateEventUser(Long id, EventUser event) throws EventUserException;

     List<SimpleUserDetailsDTO> getEventUserByEvent(Long idEvent);
     void deleteEventUserByIdEventIdUser(Long idEvent,Long idUser) throws EventUserException;

    List<SimpleUserDTO> getPossibleUser(Long idEvent);

    List<Long> getEventByParticipant(Long idUser);

    EventUserDTO getParticipantAnswer(Long idEvent,Long idUser);

    void updateAnswer(Long idevent,Long idUser, Integer newAnswer) throws EventUserException;
}
