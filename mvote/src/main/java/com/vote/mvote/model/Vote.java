package com.vote.mvote.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vote extends BaseEntity{

    @Id
    @GeneratedValue
    private Long idVote;

    private String title;
    private Integer status;
    private Long idUser;
    private Long idSurvey;
    private Long idSurveyValue;

}
