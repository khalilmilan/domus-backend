package com.notification.mnotification.service;

import com.notification.mnotification.dto.NotificationDTO;
import com.notification.mnotification.exception.NotificationException;
import com.notification.mnotification.model.Notification;

import java.util.List;

public interface NotificationService {

    NotificationDTO saveNotification(NotificationDTO notificationDto);

    List<NotificationDTO> getALLNotification();

    void deleteNotification(Long idNotification) throws NotificationException;

    NotificationDTO getNotification(Long idNotification) throws NotificationException;

     void updateNotification(Long idNotification, Notification notification) throws NotificationException;

    List<NotificationDTO> getNotificationsbySender(Long idSender);
    List<NotificationDTO> getNotificationsByReciver(Long idReciver);

}
