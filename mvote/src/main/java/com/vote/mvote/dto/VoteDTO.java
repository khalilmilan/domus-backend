package com.vote.mvote.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VoteDTO {

    private Long idVote;
    private String title;
    private Integer status;
    private Long idUser;
    private Long idSurvey;
    private Long idSurveyValue;
}
