package com.msurvey.msurvey.service;

import com.msurvey.msurvey.client.UserServiceClient;
import com.msurvey.msurvey.client.VoteFeignClient;
import com.msurvey.msurvey.dto.SimpleUserDTO;
import com.msurvey.msurvey.dto.SurveyDTO;
import com.msurvey.msurvey.dto.SurveyValueDTO;
import com.msurvey.msurvey.dto.VoteDTO;
import com.msurvey.msurvey.exception.SurveyException;
import com.msurvey.msurvey.mapper.SurveyMapper;
import com.msurvey.msurvey.model.Survey;
import com.msurvey.msurvey.repository.SurveyRepository;
import com.msurvey.msurvey.client.SurveyValueFeignClient;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SurveyServiceImple implements SurveyService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SurveyServiceImple.class);

    private SurveyRepository surveyRepository;
    private UserServiceClient userFeignClient;
    private SurveyValueFeignClient surveyValueFeignClient;

    private VoteFeignClient voteFeignClient;
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
                SimpleUserDTO userDto = userFeignClient.getSimpleUser(dto.getIdUser());
                dto.setUser(userDto);
                List<SurveyValueDTO> values = surveyValueFeignClient.getSurveyesBySurvey(dto.getIdSurvey());
                dto.setValues(values);
                List<VoteDTO> votes = voteFeignClient.getVoteBySurvey(dto.getIdSurvey());
                dto.setVotes(votes);
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
            SimpleUserDTO userDto = userFeignClient.getSimpleUser(dto.getIdUser());
            dto.setUser(userDto);
            List<SurveyValueDTO> values = surveyValueFeignClient.getSurveyesBySurvey(dto.getIdSurvey());
            dto.setValues(values);
            List<VoteDTO> votes = voteFeignClient.getVoteBySurvey(dto.getIdSurvey());
            dto.setVotes(votes);
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
                SimpleUserDTO userDto = userFeignClient.getSimpleUser(dto.getIdUser());
                dto.setUser(userDto);
                List<SurveyValueDTO> values = surveyValueFeignClient.getSurveyesBySurvey(dto.getIdSurvey());
                dto.setValues(values);
                List<VoteDTO> votes = voteFeignClient.getVoteBySurvey(dto.getIdSurvey());
                dto.setVotes(votes);
                surveysDto.add(dto);
            }
            return surveysDto;
        }else {
            return new ArrayList<SurveyDTO>();
        }
    }
}
