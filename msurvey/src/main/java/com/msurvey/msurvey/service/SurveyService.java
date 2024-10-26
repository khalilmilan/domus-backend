package com.msurvey.msurvey.service;

import com.msurvey.msurvey.dto.SurveyDTO;
import com.msurvey.msurvey.exception.SurveyException;
import com.msurvey.msurvey.model.Survey;
import org.w3c.dom.events.EventException;

import java.util.List;

public interface SurveyService {

    SurveyDTO saveSurvey(SurveyDTO surveyDto);
    List<SurveyDTO> getALLSurvey();
    void deleteSurvey(Long idSurvey) throws SurveyException;
    SurveyDTO getSurvey(Long idSurvey) throws SurveyException;
    void updateSurvey(Long id, Survey survey) throws SurveyException;
    List<SurveyDTO> getALLSurveyByUser(Long idUser);
    int getCountSurveyByUser(Long idUser);
    List<SurveyDTO> getALLSurveyByParticipant(Long idUser);


}
