package com.commentaire.mcommentaire.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentaireDTO {

    private Long idCommentaire;
    private String content;
    private Date date;
    private Integer Status;
    private Long idUser;
    private Long idForum;
    private SimpleUserDTO user;
}
