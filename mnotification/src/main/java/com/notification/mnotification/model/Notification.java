package com.notification.mnotification.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification extends BaseEntity{

    @Id
    @GeneratedValue
    private Long idNotification;

    private String title;
    private String description;
    private Integer Type;
    private Date date;
    private Integer status;
    private Long idSender;
    private Long idReciver;
}
