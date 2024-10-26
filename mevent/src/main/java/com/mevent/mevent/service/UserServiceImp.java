package com.mevent.mevent.service;

import com.mevent.mevent.client.UserServiceClient;
import com.mevent.mevent.dto.*;
import com.mevent.mevent.eventMapper.EventMapper;
import com.mevent.mevent.exception.EventException;
import com.mevent.mevent.model.Event;
import com.mevent.mevent.repository.EventRepository;
import com.mevent.mevent.client.EventUserFeignClient;
import com.mevent.mevent.client.ForumFeignClient;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImp implements IUserService{

    private EventRepository eventRepository;
    private UserServiceClient userFeignClient;
    private EventUserFeignClient eventUserFeignClient;
    private ForumFeignClient forumFeignClient;
    @Override
    public EventDetailsDTO fetchUserDetails(Long idEvent) throws EventException{
        Optional<Event> eventOptional = eventRepository.findById(idEvent);
        if (!eventOptional.isPresent()) {
            throw new EventException(EventException.NotFoundException(idEvent));
        }else {
            EventDTO eventDto = EventMapper.mapToEventDto(eventOptional.get());
            EventDetailsDTO  eventDetailsDTO = EventMapper.mapToEventDetailsDto(eventDto);
            SimpleUserDTO userDto = userFeignClient.getSimpleUser(eventDto.getIdUser());
            eventDetailsDTO.setUser(userDto);
            List<SimpleUserDetailsDTO> participants =  eventUserFeignClient.getAllEventsUser(eventDetailsDTO.getIdEvent());
            eventDetailsDTO.setParticipants(participants);
            List<ForumDTO> forums = forumFeignClient.getAllForumByEvent(eventDetailsDTO.getIdEvent());
            eventDetailsDTO.setForums(forums);
            return eventDetailsDTO;
        }
    }
}
