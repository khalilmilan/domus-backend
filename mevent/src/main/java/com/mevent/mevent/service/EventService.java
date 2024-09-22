package com.mevent.mevent.service;

import com.mevent.mevent.dto.EventDTO;
import com.mevent.mevent.exception.EventException;
import com.mevent.mevent.model.Event;

import java.util.List;

public interface EventService {
    EventDTO saveEvent(EventDTO eventDto);

    List<EventDTO> getALLEvent();

    void deleteEvent(Long idEvent) throws EventException;

    EventDTO getEvent(Long idEvent) throws EventException;

     void updateEvent(Long id, Event event) throws EventException;

     void addPartipant(Long idEvent, Long idUser) throws EventException;

     void removeParticiapant(Long idEvent, Long idUser) throws EventException;
}
