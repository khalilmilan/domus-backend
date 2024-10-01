package com.notification.mnotification.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO {

    private Long idNotification;

    private String title;
    private String description;
    private Integer Type;
    private Date date;
    private Integer status;
    private Long idSender;
    private Long idReciver;
    private SimpleUserDTO sender;
    private SimpleUserDTO reciver;

}
