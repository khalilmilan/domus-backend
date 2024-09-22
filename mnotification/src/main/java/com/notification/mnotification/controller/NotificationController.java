package com.notification.mnotification.controller;

import com.notification.mnotification.dto.NotificationDTO;
import com.notification.mnotification.exception.NotificationException;
import com.notification.mnotification.model.Notification;
import com.notification.mnotification.service.NotificationService;
import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin("*")
@RequestMapping("notification")
@AllArgsConstructor
public class NotificationController {

    private NotificationService notificationService;
    @GetMapping(value = "/lowel")
    public String test(){
        return "hi";
    }
    @PostMapping
    public ResponseEntity<NotificationDTO> saveNotification(@RequestBody NotificationDTO notificationDto){
        NotificationDTO savedNotification = notificationService.saveNotification(notificationDto);
        return new ResponseEntity<>(savedNotification, HttpStatus.CREATED);
    }
    @GetMapping("")
    public ResponseEntity<?> getAllNotification() {
        List<NotificationDTO> notifications = notificationService.getALLNotification();
        return new ResponseEntity<>(notifications, notifications.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{idNotification}")
    public ResponseEntity<?> getNotification(@PathVariable("idNotification") Long idNotification) throws NotificationException {
        try {
            return new ResponseEntity<>(notificationService.getNotification(idNotification), HttpStatus.OK);
        } catch (NotificationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{idNotification}")
    public ResponseEntity<?> deleteById(@PathVariable("idNotification") Long idNotification) throws NotificationException{
        try{
            notificationService.deleteNotification(idNotification);
            return new ResponseEntity<>("Successfully deleted notification with id "+idNotification, HttpStatus.OK);
        }
        catch (NotificationException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{idNotification}")
    public ResponseEntity<?> updateById(@PathVariable("idNotification") Long idNotification, @RequestBody Notification notification)
    {
        try {
            notificationService.updateNotification(idNotification,notification);
            return new ResponseEntity<>("Updated notification with id "+idNotification+"", HttpStatus.OK);
        }
        catch(ConstraintViolationException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch (NotificationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/by_sender/{idSender}")
    public ResponseEntity<List<NotificationDTO>> getNotificationBySender(@PathVariable("idSender") Long idSender) {
        List<NotificationDTO> notifications = notificationService.getNotificationsbySender(idSender);
        return new ResponseEntity<>(notifications, notifications.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
    @GetMapping("/by_reciver/{idReciver}")
    public ResponseEntity<List<NotificationDTO>> getNotificationByReciver(@PathVariable("idReciver") Long idReciver) {
        List<NotificationDTO> notifications = notificationService.getNotificationsByReciver(idReciver);
        return new ResponseEntity<>(notifications, notifications.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
