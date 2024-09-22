package com.commentaire.mcommentaire.service.client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ForumDTO {

    private Long idForum;

    private String title;

    private String description;
    private Integer status;
    private Long idUser;
    private Long idEvent;

    private UserDTO user;
    private EventDTO event;

}
