package com.mevent.mevent.eventMapper;

import com.mevent.mevent.dto.*;
import com.mevent.mevent.model.Event;

import java.util.ArrayList;

public class EventMapper {

    public static EventDTO mapToEventDto(Event event){
        EventDTO eventDto = new EventDTO(
                event.getIdEvent(),
                event.getLabel(),
                event.getDescription(),
                event.getIdUser(),
                event.getDate(),
                event.getStatus(),
                new SimpleUserDTO(),
                new ArrayList<>(),
                new ArrayList<>()

        );
        return eventDto;
    }

    public static EventDetailsDTO mapToEventDetailsDto(EventDTO eventDto){
        EventDetailsDTO eventDetailsDto = new EventDetailsDTO(
                eventDto.getIdEvent(),
                eventDto.getLabel(),
                eventDto.getDescription(),
                eventDto.getIdUser(),
                eventDto.getDate(),
                eventDto.getStatus(),
                eventDto.getUser(),
                eventDto.getParticipants(),
                eventDto.getForums()
        );
        return eventDetailsDto;
    }

    public static SimpleEventDTO mapToSimpleEventDto(Event event){
        SimpleEventDTO simpleEventDTO = new SimpleEventDTO(
                event.getIdEvent(),
                event.getLabel(),
                event.getDescription(),
                event.getIdUser(),
                event.getDate(),
                event.getStatus(),
                new SimpleUserDTO()

        );
        return simpleEventDTO;
    }

    public static Event mapToEvent(EventDTO eventDto){
        Event event = new Event(
                eventDto.getIdEvent(),
                eventDto.getLabel(),
                eventDto.getDescription(),
                eventDto.getIdUser(),
                eventDto.getDate(),
                eventDto.getStatus()
        );
        return event;
    }
}
