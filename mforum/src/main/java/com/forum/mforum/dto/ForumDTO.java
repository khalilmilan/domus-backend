package com.forum.mforum.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

    private List<CommentaireDTO> comments;

}
