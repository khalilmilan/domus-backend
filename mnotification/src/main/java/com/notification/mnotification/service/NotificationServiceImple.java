package com.notification.mnotification.service;

import com.notification.mnotification.client.UserServiceClient;
import com.notification.mnotification.dto.NotificationDTO;
import com.notification.mnotification.dto.SimpleUserDTO;
import com.notification.mnotification.exception.NotificationException;
import com.notification.mnotification.model.Notification;
import com.notification.mnotification.notificationMapper.NotificationMapper;
import com.notification.mnotification.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class NotificationServiceImple implements NotificationService{

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationServiceImple.class);

    private NotificationRepository notificationRepository;
    private UserServiceClient userFeignClient;
    @Override
    public NotificationDTO saveNotification(NotificationDTO notificationDto) {
        Notification notification = NotificationMapper.mapToNotification(notificationDto);
        notification.setDate(new Date());
        Notification savedNotification= notificationRepository.save(notification);
        NotificationDTO savedNotificationDto = NotificationMapper.mapToNotificationDto(savedNotification);
        return savedNotificationDto;
    }

    @Override
    public List<NotificationDTO> getALLNotification() {
        List<Notification> notifications = notificationRepository.findAll();
        List<NotificationDTO> notificationDto= new ArrayList<>();
        if (notifications.size() > 0) {
            for (Notification notification : notifications) {
                NotificationDTO dto =NotificationMapper.mapToNotificationDto(notification);
                SimpleUserDTO sender = userFeignClient.getSimpleUser(dto.getIdSender());
                dto.setSender(sender);
                SimpleUserDTO reciver = userFeignClient.getSimpleUser(dto.getIdReciver());
                dto.setReciver(reciver);
                notificationDto.add(dto);
            }
            return notificationDto;
        }else {
            return new ArrayList<NotificationDTO>();
        }
    }

    @Override
    public void deleteNotification(Long idNotification) throws NotificationException {
        Optional<Notification> notificationOptional = notificationRepository.findById(idNotification);
        if (!notificationOptional.isPresent()) {
            throw new NotificationException(NotificationException.NotFoundException(idNotification));
        } else {
            notificationRepository.deleteById(idNotification);
        }
    }

    @Override
    public NotificationDTO getNotification(Long idNotification) throws NotificationException {
        Optional<Notification> notificationOptional = notificationRepository.findById(idNotification);
        if (!notificationOptional.isPresent()) {
            throw new NotificationException(NotificationException.NotFoundException(idNotification));
        }else {
            NotificationDTO dto = NotificationMapper.mapToNotificationDto(notificationOptional.get());
            SimpleUserDTO sender = userFeignClient.getSimpleUser(dto.getIdSender());
            dto.setSender(sender);
            SimpleUserDTO reciver = userFeignClient.getSimpleUser(dto.getIdReciver());
            dto.setReciver(reciver);
            return dto;
        }
    }

    @Override
    public void updateNotification(Long idNotification, Notification notification) throws NotificationException {
        Optional<Notification> notificationWithId = notificationRepository.findById(idNotification);
        if(notificationWithId.isPresent())
        {
            Notification notificationToUpdate = notificationWithId.get();
            notificationToUpdate.setTitle(notification.getTitle());
            notificationToUpdate.setDescription(notification.getDescription());
            notificationToUpdate.setType(notification.getType());
            notificationToUpdate.setStatus(notification.getStatus());
            notificationRepository.save(notificationToUpdate);
        }
        else
        {
            throw new NotificationException(NotificationException.NotFoundException(idNotification));
        }
    }

    @Override
    public List<NotificationDTO> getNotificationsbySender(Long idSender) {
        List<Notification> notifications = notificationRepository.findNotificationsByIdSender(idSender);
        List<NotificationDTO> notificationDto= new ArrayList<>();
        if (notifications.size() > 0) {
            for (Notification notification : notifications) {
                NotificationDTO dto =NotificationMapper.mapToNotificationDto(notification);
                SimpleUserDTO reciver = userFeignClient.getSimpleUser(dto.getIdReciver());
                dto.setReciver(reciver);
                notificationDto.add(dto);
            }
            return notificationDto;
        }else {
            return new ArrayList<NotificationDTO>();
        }
    }

    @Override
    public List<NotificationDTO> getNotificationsByReciver(Long idReciver) {
        List<Notification> notifications = notificationRepository.findNotificationsByIdSender(idReciver);
        List<NotificationDTO> notificationDto= new ArrayList<>();
        if (notifications.size() > 0) {
            for (Notification notification : notifications) {
                NotificationDTO dto =NotificationMapper.mapToNotificationDto(notification);
                SimpleUserDTO sender = userFeignClient.getSimpleUser(dto.getIdSender());
                dto.setSender(sender);
                notificationDto.add(dto);
            }
            return notificationDto;
        }else {
            return new ArrayList<NotificationDTO>();
        }
    }
}
