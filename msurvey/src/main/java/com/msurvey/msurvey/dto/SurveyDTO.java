package com.msurvey.msurvey.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SurveyDTO {
    private Long idSurvey;
    private String title;
    private String description;
    private Integer status;
    private Long idUser;
    private SimpleUserDTO user;
    private List<SurveyValueDTO> values;
    private List<VoteDTO> votes;
}
