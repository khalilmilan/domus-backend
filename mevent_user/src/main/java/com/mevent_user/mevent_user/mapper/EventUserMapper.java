package com.mevent_user.mevent_user.mapper;

import com.mevent_user.mevent_user.dto.EventUserDTO;
import com.mevent_user.mevent_user.model.EventUser;

public class EventUserMapper {

    public static EventUserDTO mapToEventUserDto(EventUser eventUser){
        EventUserDTO eventDto = new EventUserDTO(
                eventUser.getIdEventUser(),
                eventUser.getIdEvent(),
                eventUser.getIdUser(),
                eventUser.getStatus()
        );
        return eventDto;
    }

    public static EventUser mapToEventUser(EventUserDTO eventUserDto){
        EventUser event = new EventUser(
                eventUserDto.getIdEventUser(),
                eventUserDto.getIdEvent(),
                eventUserDto.getIdUser(),
                eventUserDto.getStatus()
        );
        return event;
    }
}
