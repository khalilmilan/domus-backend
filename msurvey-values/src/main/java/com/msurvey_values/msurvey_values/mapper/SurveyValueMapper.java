package com.msurvey_values.msurvey_values.mapper;

import com.msurvey_values.msurvey_values.dto.SimpleUserDTO;
import com.msurvey_values.msurvey_values.dto.SurveyValueDTO;
import com.msurvey_values.msurvey_values.model.SurveyValue;

public class SurveyValueMapper {
    public static SurveyValueDTO mapToSurveyValueDto(SurveyValue surveyValue){
        SurveyValueDTO surveryValueDto = new SurveyValueDTO(
                surveyValue.getIdSurveyValue(),
                surveyValue.getTitle(),
                surveyValue.getDescription(),
                surveyValue.getStatus(),
                surveyValue.getIdSurvey(),
                surveyValue.getIdUser(),
                new SimpleUserDTO()
        );
        return surveryValueDto;
    }

    public static SurveyValue mapToSurveyValue(SurveyValueDTO surveyDto){
        SurveyValue surveyValue = new SurveyValue(
                surveyDto.getIdSurveyValue(),
                surveyDto.getTitle(),
                surveyDto.getDescription(),
                surveyDto.getStatus(),
                surveyDto.getIdSurvey(),
                surveyDto.getIdUser()
        );
        return surveyValue;
    }
}
