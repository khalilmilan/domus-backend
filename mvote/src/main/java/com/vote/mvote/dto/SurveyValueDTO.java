package com.vote.mvote.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SurveyValueDTO {
    private Long idSurveyValue;
    private String title;
    private String description;
    private Integer status;
    private String colorCode;
    private Long idSurvey;
    private Long idUser;
    private SimpleUserDTO user;
}
