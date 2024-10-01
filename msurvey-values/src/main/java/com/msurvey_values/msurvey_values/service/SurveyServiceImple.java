package com.msurvey_values.msurvey_values.service;

import com.msurvey_values.msurvey_values.client.UserServiceClient;
import com.msurvey_values.msurvey_values.dto.SimpleUserDTO;
import com.msurvey_values.msurvey_values.dto.SurveyValueDTO;
import com.msurvey_values.msurvey_values.exception.SurveyValueException;
import com.msurvey_values.msurvey_values.mapper.SurveyValueMapper;
import com.msurvey_values.msurvey_values.model.SurveyValue;
import com.msurvey_values.msurvey_values.repository.SurveyValueRepository;
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
public class SurveyServiceImple implements SurveyValueService{
    private static final Logger LOGGER = LoggerFactory.getLogger(SurveyServiceImple.class);
    private UserServiceClient userFeignClient;
    private SurveyValueRepository surveyValueRepository;
    @Override
    public SurveyValueDTO saveSurveyValue(SurveyValueDTO surveyValueDto) {
        SurveyValue surveyValue = SurveyValueMapper.mapToSurveyValue(surveyValueDto);
        surveyValue.setCreatedAt(LocalDateTime.now());
        SurveyValue savedSurveyValue = surveyValueRepository.save(surveyValue);
        SurveyValueDTO savedSurveyValueDto = SurveyValueMapper.mapToSurveyValueDto(savedSurveyValue);
        return savedSurveyValueDto;
    }

    @Override
    public List<SurveyValueDTO> getALLSurveyValue() {
        List<SurveyValue> surveyValues = surveyValueRepository.findAll();
        List<SurveyValueDTO> surveyValuesDto= new ArrayList<>();
        if (surveyValues.size() > 0) {
            for (SurveyValue surveyValue : surveyValues) {
                SurveyValueDTO dto = SurveyValueMapper.mapToSurveyValueDto(surveyValue);
                SimpleUserDTO userDto = userFeignClient.getSimpleUser(dto.getIdUser());
                dto.setUser(userDto);
                surveyValuesDto.add(dto);
            }
            return surveyValuesDto;
        }else {
            return new ArrayList<SurveyValueDTO>();
        }
    }

    @Override
    public void deleteSurveyValue(Long idSurveyValue) throws SurveyValueException {
        Optional<SurveyValue> surveyValueOptional = surveyValueRepository.findById(idSurveyValue);
        if (!surveyValueOptional.isPresent()) {
            throw new SurveyValueException(SurveyValueException.NotFoundException(idSurveyValue));
        } else {
            surveyValueRepository.deleteById(idSurveyValue);
        }
    }

    @Override
    public SurveyValueDTO getSurveyValue(Long idSurveyValue) throws SurveyValueException {
        Optional<SurveyValue> surveyValueOptional = surveyValueRepository.findById(idSurveyValue);
        if (!surveyValueOptional.isPresent()) {
            throw new SurveyValueException(SurveyValueException.NotFoundException(idSurveyValue));
        }else {
            SurveyValueDTO dto =SurveyValueMapper.mapToSurveyValueDto(surveyValueOptional.get());
            SimpleUserDTO userDto = userFeignClient.getSimpleUser(dto.getIdUser());
            dto.setUser(userDto);
            return dto;
        }
    }

    @Override
    public void updateSurveyValue(Long id, SurveyValue surveyValue) throws SurveyValueException {
        Optional<SurveyValue> surveyValueWithId = surveyValueRepository.findById(id);
        if(surveyValueWithId.isPresent())
        {
            SurveyValue surveyValueToUpdate=surveyValueWithId.get();
            surveyValueToUpdate.setTitle(surveyValue.getTitle());
            surveyValueToUpdate.setDescription(surveyValue.getDescription());
            surveyValueToUpdate.setStatus(surveyValue.getStatus());
            surveyValueToUpdate.setUpdatedAt(LocalDateTime.now());
            surveyValueRepository.save(surveyValueToUpdate);
        }
        else
        {
            throw new SurveyValueException(SurveyValueException.NotFoundException(id));
        }
    }

    @Override
    public List<SurveyValueDTO> getALLSurveyValueBySurvey(Long idSurvey) {
        List<SurveyValue> surveyvalues = surveyValueRepository.findSurveyValuesByIdSurvey(idSurvey);
        List<SurveyValueDTO> surveysDto= new ArrayList<>();
        if (surveyvalues.size() > 0) {
            for (SurveyValue surveyValue : surveyvalues) {
                SurveyValueDTO dto = SurveyValueMapper.mapToSurveyValueDto(surveyValue);
                SimpleUserDTO userDto = userFeignClient.getSimpleUser(dto.getIdUser());
                dto.setUser(userDto);
                surveysDto.add(dto);
            }
            return surveysDto;
        }else {
            return new ArrayList<SurveyValueDTO>();
        }
    }

    @Override
    public List<SurveyValueDTO> getALLSurveyValueByUser(Long idUser) {
        List<SurveyValue> surveyValues = surveyValueRepository.findSurveyValuesByIdUser(idUser);
        List<SurveyValueDTO> surveysDto= new ArrayList<>();
        if (surveyValues.size() > 0) {
            for (SurveyValue surveyValue : surveyValues) {
                SurveyValueDTO dto = SurveyValueMapper.mapToSurveyValueDto(surveyValue);
                SimpleUserDTO userDto = userFeignClient.getSimpleUser(dto.getIdUser());
                dto.setUser(userDto);
                surveysDto.add(dto);
            }
            return surveysDto;
        }else {
            return new ArrayList<SurveyValueDTO>();
        }
    }
}
