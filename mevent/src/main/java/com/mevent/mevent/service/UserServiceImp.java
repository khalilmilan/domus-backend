package com.mevent.mevent.service;

import com.mevent.mevent.dto.EventDTO;
import com.mevent.mevent.dto.EventDetailsDTO;
import com.mevent.mevent.dto.ForumDTO;
import com.mevent.mevent.dto.UserDTO;
import com.mevent.mevent.eventMapper.EventMapper;
import com.mevent.mevent.exception.EventException;
import com.mevent.mevent.model.Event;
import com.mevent.mevent.repository.EventRepository;
import com.mevent.mevent.service.client.EventUserFeignClient;
import com.mevent.mevent.service.client.ForumFeignClient;
import com.mevent.mevent.service.client.UserFeignClient;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImp implements IUserService{

    private EventRepository eventRepository;
    private UserFeignClient userFeignClient;
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
            UserDTO userDto = userFeignClient.getUser(eventDto.getIdUser()).getBody();
            eventDetailsDTO.setUser(userDto);
            List<UserDTO> participants =  eventUserFeignClient.getAllEventsUser(eventDetailsDTO.getIdEvent()).getBody();
            eventDetailsDTO.setParticipants(participants);
            List<ForumDTO> forums = forumFeignClient.getAllForumByEvent(eventDetailsDTO.getIdEvent()).getBody();
            eventDetailsDTO.setForums(forums);
            return eventDetailsDTO;
        }
    }
}
