package com.commentaire.mcommentaire.service;

import com.commentaire.mcommentaire.dto.CommentaireDTO;
import com.commentaire.mcommentaire.exception.CommentaireException;
import com.commentaire.mcommentaire.model.Commentaire;

import java.util.List;

public interface CommentaireService {

    CommentaireDTO saveCommentaire(CommentaireDTO commentaireDto);

    List<CommentaireDTO> getALLCommentaire();

    void deleteCommentaire(Long idCommentaire) throws CommentaireException;

    CommentaireDTO getCommentaire(Long idCommentaire) throws CommentaireException;

     void updateCommentaire(Long idCommentaire, Commentaire commentaire) throws CommentaireException;
    List<CommentaireDTO> getCommentByForum(Long idForum);
}
