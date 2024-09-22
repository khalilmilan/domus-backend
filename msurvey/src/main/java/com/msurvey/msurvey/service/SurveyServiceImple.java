package com.msurvey.msurvey.service;

import com.msurvey.msurvey.dto.SurveyDTO;
import com.msurvey.msurvey.dto.SurveyValueDTO;
import com.msurvey.msurvey.dto.UserDTO;
import com.msurvey.msurvey.exception.SurveyException;
import com.msurvey.msurvey.mapper.SurveyMapper;
import com.msurvey.msurvey.model.Survey;
import com.msurvey.msurvey.repository.SurveyRepository;
import com.msurvey.msurvey.service.client.SurveyValueFeignClient;
import com.msurvey.msurvey.service.client.UserFeignClient;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.w3c.dom.events.EventException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SurveyServiceImple implements SurveyService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SurveyServiceImple.class);

    private SurveyRepository surveyRepository;
    private UserFeignClient userFeignClient;
    private SurveyValueFeignClient surveyValueFeignClient;
    @Override
    public SurveyDTO saveSurvey(SurveyDTO surveyDto) {
        Survey survey = SurveyMapper.mapToSurvey(surveyDto);
        survey.setCreatedAt(LocalDateTime.now());
        Survey savedSurvey = surveyRepository.save(survey);
        SurveyDTO savedSurveyDto = SurveyMapper.mapToSurveyDto(savedSurvey);
        return savedSurveyDto;
    }

    @Override
    public List<SurveyDTO> getALLSurvey() {
        List<Survey> surveys = surveyRepository.findAll();
        List<SurveyDTO> surveysDto= new ArrayList<>();
        if (surveys.size() > 0) {
            for (Survey survey : surveys) {
                SurveyDTO dto = SurveyMapper.mapToSurveyDto(survey);
                UserDTO userDto = userFeignClient.getUser(dto.getIdUser()).getBody();
                dto.setUser(userDto);
                surveysDto.add(dto);
            }
            return surveysDto;
        }else {
            return new ArrayList<SurveyDTO>();
        }
    }

    @Override
    public void deleteSurvey(Long idSurvey) throws SurveyException {
        Optional<Survey> surveyOptional = surveyRepository.findById(idSurvey);
        if (!surveyOptional.isPresent()) {
            throw new SurveyException(SurveyException.NotFoundException(idSurvey));
        } else {
            surveyRepository.deleteById(idSurvey);
        }
    }

    @Override
    public SurveyDTO getSurvey(Long idSurvey) throws SurveyException {
        Optional<Survey> surveyOptional = surveyRepository.findById(idSurvey);
        if (!surveyOptional.isPresent()) {
            throw new SurveyException(SurveyException.NotFoundException(idSurvey));
        }else {
            SurveyDTO dto =SurveyMapper.mapToSurveyDto(surveyOptional.get());
            UserDTO userDto = userFeignClient.getUser(dto.getIdUser()).getBody();
            dto.setUser(userDto);
            List<SurveyValueDTO> values = surveyValueFeignClient.getSurveyesBySurvey(dto.getIdSurvey()).getBody();
            dto.setValues(values);
            return dto;
        }
    }

    @Override
    public void updateSurvey(Long id, Survey survey) throws SurveyException {
        Optional<Survey> surveyWithId = surveyRepository.findById(id);
        if(surveyWithId.isPresent())
        {
            Survey surveyToUpdate=surveyWithId.get();
            surveyToUpdate.setTitle(survey.getTitle());
            surveyToUpdate.setDescription(survey.getDescription());
            surveyToUpdate.setStatus(survey.getStatus());
            survey.setUpdatedAt(LocalDateTime.now());
            surveyRepository.save(surveyToUpdate);
        }
        else
        {
            throw new SurveyException(SurveyException.NotFoundException(id));
        }
    }

    @Override
    public List<SurveyDTO> getALLSurveyByUser(Long idUser) {
        List<Survey> surveys = surveyRepository.findSurveyByIdUser(idUser);
        List<SurveyDTO> surveysDto= new ArrayList<>();
        if (surveys.size() > 0) {
            for (Survey survey : surveys) {
                SurveyDTO dto = SurveyMapper.mapToSurveyDto(survey);
                UserDTO userDto = userFeignClient.getUser(dto.getIdUser()).getBody();
                dto.setUser(userDto);
                surveysDto.add(dto);
            }
            return surveysDto;
        }else {
            return new ArrayList<SurveyDTO>();
        }
    }
}
