package com.commentaire.mcommentaire.repository;

import com.commentaire.mcommentaire.dto.CommentaireDTO;
import com.commentaire.mcommentaire.model.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentaireRepository extends JpaRepository<Commentaire,Long> {

    List<Commentaire> findCommetaireByIdForum(Long idForum);
}
