package com.commentaire.mcommentaire.model;

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
public class Commentaire extends BaseEntity{

    @Id
    @GeneratedValue
    private Long idCommentaire;
    private String content;
    private Date date;
    private Integer Status;
    private Long idUser;
    private Long idForum;

}
