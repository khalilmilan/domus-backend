package com.mticket.mticket.dto;

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
    private String token;
    private String imageUrl;    // URL de l'image (optionnel)
    private Integer badgeCount; // Nombre de notifications non lues
    private SimpleUserDTO sender;
    private SimpleUserDTO reciver;

}
