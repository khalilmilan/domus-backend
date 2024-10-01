package com.msurvey.msurvey.mapper;

import com.msurvey.msurvey.dto.SimpleUserDTO;
import com.msurvey.msurvey.dto.SurveyDTO;
import com.msurvey.msurvey.model.Survey;

import java.util.ArrayList;

public class SurveyMapper {
    public static SurveyDTO mapToSurveyDto(Survey survey){
        SurveyDTO eventDto = new SurveyDTO(
                survey.getIdSurvey(),
                survey.getTitle(),
                survey.getDescription(),
                survey.getStatus(),
                survey.getIdUser(),
                new SimpleUserDTO(),
                new ArrayList<>(),
                new ArrayList<>()

        );
        return eventDto;
    }

    public static Survey mapToSurvey(SurveyDTO surveyDto){
        Survey survey = new Survey(
                surveyDto.getIdSurvey(),
                surveyDto.getTitle(),
                surveyDto.getDescription(),
                surveyDto.getStatus(),
                surveyDto.getIdUser()
        );
        return survey;
    }
}
