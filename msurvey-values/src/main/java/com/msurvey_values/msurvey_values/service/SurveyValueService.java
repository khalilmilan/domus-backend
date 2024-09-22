package com.msurvey_values.msurvey_values.service;

import com.msurvey_values.msurvey_values.dto.SurveyValueDTO;
import com.msurvey_values.msurvey_values.exception.SurveyValueException;
import com.msurvey_values.msurvey_values.model.SurveyValue;

import java.util.List;

public interface SurveyValueService {

    SurveyValueDTO saveSurveyValue(SurveyValueDTO surveyValueDto );

    List<SurveyValueDTO> getALLSurveyValue();

    void deleteSurveyValue(Long idSurveyValue) throws SurveyValueException;

    SurveyValueDTO getSurveyValue(Long idSurveyValue) throws SurveyValueException;

    void updateSurveyValue(Long id, SurveyValue surveyValue) throws SurveyValueException;

    List<SurveyValueDTO> getALLSurveyValueBySurvey(Long idSurvey);
    List<SurveyValueDTO> getALLSurveyValueByUser(Long idUser);
}
