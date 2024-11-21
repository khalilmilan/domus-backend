package com.msurvey.msurvey.mapper;

import com.msurvey.msurvey.dto.*;
import com.msurvey.msurvey.model.Survey;

import java.util.ArrayList;

public class SurveyMapper {
    public static SurveyDTO mapToSurveyDto(Survey survey){
        SurveyDTO surveyDto = new SurveyDTO(
                survey.getIdSurvey(),
                survey.getTitle(),
                survey.getDescription(),
                survey.getStatus(),
                survey.getStartDate(),
                survey.getEndDate(),
                survey.getIdUser(),
                new SimpleUserDTO(),
                survey.getIdGroupe(),
                new SimpleGroupeDTO(),
                new ArrayList<>(),
                new ArrayList<>()

        );
        return surveyDto;
    }

    public static SimpleSurveyDTO mapToSimpleSurveyDto(Survey survey){
        SimpleSurveyDTO surveyDto = new SimpleSurveyDTO(
                survey.getIdSurvey(),
                survey.getTitle(),
                survey.getDescription(),
                survey.getStatus(),
                survey.getStartDate(),
                survey.getEndDate(),
                survey.getIdUser(),
                new SimpleUserDTO()
        );
        return surveyDto;
    }

    public static Survey mapToSurvey(SurveyDTO surveyDto){
        Survey survey = new Survey(
                surveyDto.getIdSurvey(),
                surveyDto.getTitle(),
                surveyDto.getDescription(),
                surveyDto.getStatus(),
                surveyDto.getStartDate(),
                surveyDto.getEndDate(),
                surveyDto.getIdUser(),
                surveyDto.getIdGroupe()
        );
        return survey;
    }
}
