package com.notification.mnotification.notificationMapper;

import com.notification.mnotification.dto.NotificationDTO;
import com.notification.mnotification.dto.SimpleUserDTO;
import com.notification.mnotification.model.Notification;

public class NotificationMapper {

    public static NotificationDTO mapToNotificationDto(Notification notification){
        NotificationDTO notificationDto = new NotificationDTO(
                notification.getIdNotification(),
                notification.getTitle(),
                notification.getDescription(),
                notification.getType(),
                notification.getDate(),
                notification.getStatus(),
                notification.getIdSender(),
                notification.getIdReciver(),
                new SimpleUserDTO(),
                new SimpleUserDTO()
        );
        return notificationDto;
    }

    public static Notification mapToNotification(NotificationDTO notificationDto){
        Notification notification = new Notification(
                notificationDto.getIdNotification(),
                notificationDto.getTitle(),
                notificationDto.getDescription(),
                notificationDto.getType(),
                notificationDto.getDate(),
                notificationDto.getStatus(),
                notificationDto.getIdSender(),
                notificationDto.getIdReciver()
        );
        return notification;
    }
}
