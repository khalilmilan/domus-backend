package com.commentaire.mcommentaire.commentaireMapper;

import com.commentaire.mcommentaire.dto.CommentaireDTO;
import com.commentaire.mcommentaire.dto.SimpleUserDTO;
import com.commentaire.mcommentaire.model.Commentaire;

public class CommentaireMapper {

    public static CommentaireDTO mapToCommentaireDto(Commentaire commentaire){
        CommentaireDTO commentaireDto = new CommentaireDTO(
                commentaire.getIdCommentaire(),
                commentaire.getContent(),
                commentaire.getDate(),
                commentaire.getStatus(),
                commentaire.getIdUser(),
                commentaire.getIdForum(),
                new SimpleUserDTO()
        );
        return commentaireDto;
    }

    public static Commentaire mapToCommentaire(CommentaireDTO commentaireDto){
        Commentaire commentaire = new Commentaire(
                commentaireDto.getIdCommentaire(),
                commentaireDto.getContent(),
                commentaireDto.getDate(),
                commentaireDto.getStatus(),
                commentaireDto.getIdUser(),
                commentaireDto.getIdForum()
        );
        return commentaire;
    }
        }
