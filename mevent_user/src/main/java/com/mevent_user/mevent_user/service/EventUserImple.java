package com.mevent_user.mevent_user.service;

import com.mevent_user.mevent_user.client.EventFeignClient;
import com.mevent_user.mevent_user.client.NotificationFeignClient;
import com.mevent_user.mevent_user.client.UserDeviceFeignClient;
import com.mevent_user.mevent_user.client.UserServiceClient;
import com.mevent_user.mevent_user.dto.*;

import com.mevent_user.mevent_user.exception.EventUserException;
import com.mevent_user.mevent_user.mapper.EventUserMapper;
import com.mevent_user.mevent_user.model.EventUser;
import com.mevent_user.mevent_user.repository.EventUserRepository;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EventUserImple implements EventUserService{

    private static final Logger LOGGER = LoggerFactory.getLogger(EventUserImple.class);
    private UserServiceClient userFeignClient;
    private EventUserRepository eventUserRepository;
    private NotificationFeignClient notificationFeignClient;
    private UserDeviceFeignClient userDeviceFeignClient;
    private EventFeignClient eventFeignClient;
    @Override
    public EventUserDTO saveEvent(EventUserDTO eventUserDTO) {
        EventUser eventUser = EventUserMapper.mapToEventUser(eventUserDTO);
        EventUser savedEventUser = eventUserRepository.save(eventUser);
        EventUserDTO savedEventDto = EventUserMapper.mapToEventUserDto(savedEventUser);
        return savedEventDto;
    }

    @Override
    public List<EventUserDTO> getALLEventUser() {
        List<EventUser> events = eventUserRepository.findAll();
        List<EventUserDTO> eventUserDto= new ArrayList<>();
        if (events.size() > 0) {
            for (EventUser event : events) {
                EventUserDTO dto =EventUserMapper.mapToEventUserDto(event);
                eventUserDto.add(dto);
            }
            return eventUserDto;
        }else {
            return new ArrayList<EventUserDTO>();
        }
    }

    @Override
    public void deleteEventUser(Long idEventUser) throws EventUserException {
        Optional<EventUser> eventUserOptional = eventUserRepository.findById(idEventUser);
        if (!eventUserOptional.isPresent()) {
            throw new EventUserException(EventUserException.NotFoundException(idEventUser));
        } else {
            eventUserRepository.deleteById(idEventUser);
        }
    }

    @Override
    public EventUserDTO getEventUser(Long idEventUser) throws EventUserException {
        Optional<EventUser> eventUserOptional = eventUserRepository.findById(idEventUser);
        if (!eventUserOptional.isPresent()) {
            throw new EventUserException(EventUserException.NotFoundException(idEventUser));
        }else {
            EventUserDTO dto =EventUserMapper.mapToEventUserDto(eventUserOptional.get());
            //  List<SimpleEventDTO> ambulances = apiClient.getAmbulances(dto.getId());
            // dto.setAmbulances(ambulances);
            return dto;
        }
    }

    @Override
    public void updateEventUser(Long id, EventUser event) throws EventUserException {
        Optional<EventUser> eventUserWithId = eventUserRepository.findById(id);
        if(eventUserWithId.isPresent())
        {
            EventUser eventUserToUpdate=eventUserWithId.get();
            eventUserToUpdate.setIdEvent(event.getIdEvent());
            eventUserToUpdate.setIdUser(event.getIdUser());
            eventUserToUpdate.setStatus(event.getStatus());
            eventUserRepository.save(eventUserToUpdate);
        }
        else
        {
            throw new EventUserException(EventUserException.NotFoundException(id));
        }
    }

    @Override
    public List<SimpleUserDetailsDTO> getEventUserByEvent(Long idEvent) {
        List<EventUser> events = eventUserRepository.findEventUserByIdEvent(idEvent);;
        List<SimpleUserDetailsDTO> userDtos = new ArrayList<>();
        if (events.size() > 0) {
            for (EventUser event : events) {
                EventUserDTO dto = EventUserMapper.mapToEventUserDto(event);
                SimpleUserDTO userDto = userFeignClient.getSimpleUser(dto.getIdUser());
                SimpleUserDetailsDTO simpleUserDetailsDTO = new SimpleUserDetailsDTO();
                simpleUserDetailsDTO.setUser(userDto);
                simpleUserDetailsDTO.setAnswer(dto.getStatus());
                userDtos.add(simpleUserDetailsDTO);
            }
            return userDtos;
        }else {
            return new ArrayList<SimpleUserDetailsDTO>();
        }
    }

    @Override
    public void deleteEventUserByIdEventIdUser(Long idEvent,Long idUser) throws EventUserException {
        Optional<EventUser> eventUserOptional = Optional.ofNullable(eventUserRepository.findEventUserByIdEventAndIdUser(idEvent, idUser));
        if (!eventUserOptional.isPresent()) {
            throw new EventUserException(EventUserException.NotFoundException(idEvent));
        } else {
            eventUserRepository.deleteById(eventUserOptional.get().getIdEventUser());
        }
    }
    @Override
    public List<SimpleUserDTO> getPossibleUser(Long idEvent) {
        List<Long> userIdsInEvent = eventUserRepository.findUsersInEvent(idEvent);
        List<SimpleUserDTO> allUsers = userFeignClient.getAllSimpleUser();
        List<SimpleUserDTO> usersNotIn = allUsers.stream()
                .filter(user -> !userIdsInEvent.contains(user.getIdUser()))
                .collect(Collectors.toList());
        return usersNotIn;
    }

    @Override
    public List<Long> getEventByParticipant(Long idUser) {
        return eventUserRepository.findEventByParticipant(idUser);
    }

    @Override
    public EventUserDTO getParticipantAnswer(Long idEvent, Long idUser) {
        Optional<EventUser> eventUserOptional = Optional.ofNullable(eventUserRepository.findEventUserByIdEventAndIdUser(idEvent, idUser));
        if (eventUserOptional.isPresent()) {
           return  EventUserMapper.mapToEventUserDto(eventUserOptional.get());
        } else {
            return new EventUserDTO();
        }
    }

    @Override
    public void updateAnswer(Long idEvent, Long idUser, Integer newAnswer) throws EventUserException {
        Optional<EventUser> eventUserOptional = Optional.ofNullable(eventUserRepository.findEventUserByIdEventAndIdUser(idEvent, idUser));
        if (eventUserOptional.isPresent()) {
            EventUser eventUser = eventUserOptional.get();
            eventUser.setStatus(newAnswer);
            eventUserRepository.save(eventUser);
            SimpleUserDTO userDto = userFeignClient.getSimpleUser(idUser);
            SimpleEventDTO event = eventFeignClient.getSimpleEvent(idEvent);
            List<UserDeviceDTO> devices = userDeviceFeignClient.getDevice(event.getIdUser());
            String answer = "NO";
            if(newAnswer==2){
                answer ="maybe";
            } else if (newAnswer==3) {
                answer ="participate";
            }
            for (UserDeviceDTO device:devices){
                NotificationDTO notification = new NotificationDTO();
                notification.setTitle("📅 Answer for Event");
                notification.setDescription(userDto.getFirstName() +" "+userDto.getLastName()+ " responded to your invitation : "+answer);
                notification.setType(6);
                notification.setToken(device.getToken());
                notification.setBadgeCount(1);
                notification.setDate(new Date());
                notification.setIdReciver(event.getIdUser());
                notification.setImageUrl("https://images.unsplash.com/photo-1517423440428-a5a00ad493e8");
                notificationFeignClient.saveNotification(notification);
            }
        }
    }
}
