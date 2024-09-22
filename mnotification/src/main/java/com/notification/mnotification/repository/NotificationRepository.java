package com.notification.mnotification.repository;

import com.notification.mnotification.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
    List<Notification> findNotificationsByIdSender(Long idSender);
    List<Notification> findNotificationsByIdReciver(Long idReciver);

}
