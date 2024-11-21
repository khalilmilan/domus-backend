package com.mevent.mevent.service;

import com.mevent.mevent.client.*;
import com.mevent.mevent.dto.*;
import com.mevent.mevent.eventMapper.EventMapper;
import com.mevent.mevent.repository.EventRepository;
import com.mevent.mevent.exception.EventException;
import com.mevent.mevent.model.Event;
import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EventServiceImple implements EventService{

    private static final Logger LOGGER = LoggerFactory.getLogger(EventServiceImple.class);
    private UserServiceClient userFeignClient;
    private EventRepository eventRepository;
    private EventUserFeignClient eventUserFeignClient;
    private ForumFeignClient forumFeignClient;

    private NotificationFeignClient notificationFeignClient;
    private UserDeviceFeignClient userDeviceFeignClient;
    @Override
    public EventDTO saveEvent(EventDTO eventDto) {
        Event event = EventMapper.mapToEvent(eventDto);
        Event savedEvent = eventRepository.save(event);
        EventDTO savedEventDto = EventMapper.mapToEventDto(savedEvent);
        return savedEventDto;
    }

    @Override
    public List<EventDTO> getALLEvent() {
        List<Event> events = eventRepository.findAll();
        List<EventDTO> eventDto= new ArrayList<>();
        if (events.size() > 0) {
            for (Event event : events) {
                EventDTO dto = EventMapper.mapToEventDto(event);
                SimpleUserDTO userDto = userFeignClient.getSimpleUser(dto.getIdUser());
                dto.setUser(userDto);
                List<SimpleUserDetailsDTO> participants = eventUserFeignClient.getAllEventsUser(dto.getIdEvent());
                dto.setParticipants(participants);
                List<ForumDTO> forums = forumFeignClient.getAllForumByEvent(dto.getIdEvent());
                dto.setForums(forums);
                eventDto.add(dto);
            }
            return eventDto;
        }else {
            return new ArrayList<EventDTO>();
        }
    }

    @Override
    public void deleteEvent(Long idEvent) throws EventException {
        Optional<Event> eventOptional = eventRepository.findById(idEvent);
        if (!eventOptional.isPresent()) {
            throw new EventException(EventException.NotFoundException(idEvent));
        } else {
            eventRepository.deleteById(idEvent);
        }
    }

    @Override
    public EventDTO getEvent(Long idEvent) throws EventException {
        Optional<Event> eventOptional = eventRepository.findById(idEvent);
        if (!eventOptional.isPresent()) {
            throw new EventException(EventException.NotFoundException(idEvent));
        }else {
            EventDTO dto = EventMapper.mapToEventDto(eventOptional.get());
            SimpleUserDTO userDto = userFeignClient.getSimpleUser(dto.getIdUser());
            dto.setUser(userDto);
            List<ForumDTO> forums = forumFeignClient.getAllForumByEvent(dto.getIdEvent());
            dto.setForums(forums);
            List<SimpleUserDetailsDTO> participants = eventUserFeignClient.getAllEventsUser(dto.getIdEvent());
            dto.setParticipants(participants);
            return dto;
        }
    }

    @Override
    public void updateEvent(Long id, Event event) throws EventException {
        Optional<Event> eventWithId = eventRepository.findById(id);
        if(eventWithId.isPresent())
        {
            Event eventToUpdate=eventWithId.get();
            eventToUpdate.setLabel(event.getLabel());
            eventToUpdate.setDescription(event.getDescription());
            eventToUpdate.setStatus(event.getStatus());
            eventToUpdate.setDate(event.getDate());
            eventRepository.save(eventToUpdate);
        }
        else
        {
            throw new EventException(EventException.NotFoundException(id));
        }
    }

    @Override
    public void addPartipant(Long idEvent, Long idUser) throws EventException {
        Optional<Event> eventWithId = eventRepository.findById(idEvent);
        if(eventWithId.isPresent())
        {
            EventUserDTO eventUserDto = new EventUserDTO(
                    idEvent,
                    idUser,
                    1
            );
            EventUserDTO dto = eventUserFeignClient.saveEventUser(eventUserDto);
            List<UserDeviceDTO> devices = userDeviceFeignClient.getDevice(idUser);
            for (UserDeviceDTO device:devices){
                NotificationDTO notification = new NotificationDTO();
                notification.setTitle("📅 New Event");
                notification.setDescription("You are invited to an event "+eventWithId.get().getLabel());
                notification.setType(4);
                notification.setToken(device.getToken());
                notification.setBadgeCount(1);
                notification.setIdReciver(idUser);
                notification.setImageUrl("https://images.unsplash.com/photo-1517423440428-a5a00ad493e8");
                notification.setDate(new Date());
                notificationFeignClient.saveNotification(notification);
            }
        }
        else
        {
            throw new EventException(EventException.NotFoundException(idEvent));
        }
    }

    @Override
    public void removeParticiapant(Long idEvent, Long idUser) throws EventException {
        Optional<Event> eventWithId = eventRepository.findById(idEvent);
        if(eventWithId.isPresent())
        {
            eventUserFeignClient.deleteByEventAndUser(idEvent,idUser);
            List<UserDeviceDTO> devices = userDeviceFeignClient.getDevice(idUser);
            for (UserDeviceDTO device:devices){
                NotificationDTO notification = new NotificationDTO();
                notification.setTitle("🚫 Excluded from Event");
                notification.setDescription("You are excluded from an event "+eventWithId.get().getLabel());
                notification.setType(5);
                notification.setToken(device.getToken());
                notification.setBadgeCount(1);
                notification.setIdReciver(idUser);
                notification.setImageUrl("https://images.unsplash.com/photo-1517423440428-a5a00ad493e8");
                notification.setDate(new Date());
                notificationFeignClient.saveNotification(notification);
            }
        }
        else
        {
            throw new EventException(EventException.NotFoundException(idEvent));
        }
    }

    @Override
    public List<EventDTO> getEventsByUser(Long idUser) throws EventException {
        List<Event> events = eventRepository.findEventsByIdUser(idUser);
        List<EventDTO> eventDto= new ArrayList<>();
        if (events.size() > 0) {
            for (Event event : events) {
                EventDTO dto = EventMapper.mapToEventDto(event);
                SimpleUserDTO userDto = userFeignClient.getSimpleUser(dto.getIdUser());
                dto.setUser(userDto);
                List<SimpleUserDetailsDTO> participants = eventUserFeignClient.getAllEventsUser(dto.getIdEvent());
                dto.setParticipants(participants);
                List<ForumDTO> forums = forumFeignClient.getAllForumByEvent(dto.getIdEvent());
                dto.setForums(forums);
                eventDto.add(dto);
            }
            return eventDto;
        }else {
            return new ArrayList<EventDTO>();
        }
    }

    @Override
    public int getCountEventByUser(Long idUser) {
        return eventRepository.findEventsByIdUser(idUser).size();
    }

    @Override
    public List<EventDTO> getEventByParticipant(Long idUser) {
        List<Long> idsEvents = eventUserFeignClient.getIdsProjectByParticipant(idUser);
        List<EventDTO> eventDto = new ArrayList<EventDTO>();
        List<Event> events = eventRepository.findAllById(idsEvents);
        if (events.size() > 0) {
            for (Event event : events) {
                EventDTO dto = EventMapper.mapToEventDto(event);
                SimpleUserDTO userDto = userFeignClient.getSimpleUser(dto.getIdUser());
                dto.setUser(userDto);
                List<SimpleUserDetailsDTO> participants = eventUserFeignClient.getAllEventsUser(dto.getIdEvent());
                dto.setParticipants(participants);
                List<ForumDTO> forums = forumFeignClient.getAllForumByEvent(dto.getIdEvent());
                dto.setForums(forums);
                eventDto.add(dto);
            }
            return eventDto;
        }else {
            return new ArrayList<EventDTO>();
        }
    }

    @Override
    public SimpleEventDTO getSimpleEvent(Long idEvent) throws EventException {
        Optional<Event> eventOptional = eventRepository.findById(idEvent);
        if (!eventOptional.isPresent()) {
            throw new EventException(EventException.NotFoundException(idEvent));
        }else {
            SimpleEventDTO dto = EventMapper.mapToSimpleEventDto(eventOptional.get());
            SimpleUserDTO userDto = userFeignClient.getSimpleUser(dto.getIdUser());
            dto.setUser(userDto);
            return dto;
        }
    }
}
