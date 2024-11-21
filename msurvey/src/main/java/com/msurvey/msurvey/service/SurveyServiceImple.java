package com.msurvey.msurvey.service;

import com.msurvey.msurvey.client.*;
import com.msurvey.msurvey.dto.*;
import com.msurvey.msurvey.exception.SurveyException;
import com.msurvey.msurvey.mapper.SurveyMapper;
import com.msurvey.msurvey.model.Survey;
import com.msurvey.msurvey.repository.SurveyRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
    private UserServiceClient userFeignClient;
    private SurveyValueFeignClient surveyValueFeignClient;
    private GroupeFeignClient groupeFeignClient;
    private GroupeUserFeignClient groupeUserFeignClient;
    private VoteFeignClient voteFeignClient;
    private UserDeviceFeignClient userDeviceFeignClient;
    private NotificationFeignClient notificationFeignClient;
    @Override
    public SurveyDTO saveSurvey(SurveyDTO surveyDto) {
        Survey survey = SurveyMapper.mapToSurvey(surveyDto);
        survey.setCreatedAt(LocalDateTime.now());
        Survey savedSurvey = surveyRepository.save(survey);
        SurveyDTO savedSurveyDto = SurveyMapper.mapToSurveyDto(savedSurvey);
        GroupeDTO groupe = groupeFeignClient.getGroupe(surveyDto.getIdGroupe());
        for(SimpleUserDTO user: groupe.getMembres()){
            List<UserDeviceDTO> devices = userDeviceFeignClient.getDevice(user.getIdUser());
            for (UserDeviceDTO device:devices){
                NotificationDTO notification = new NotificationDTO();
                notification.setTitle("📊 Your Group "+groupe.getLabel()+" Added to a Survey");
                notification.setDescription(groupe.getLabel()+"Added to new Survey "+surveyDto.getTitle());
                notification.setType(11);
                notification.setIdReciver(user.getIdUser());
                notification.setToken(device.getToken());
                notification.setBadgeCount(1);
                notification.setImageUrl("https://images.unsplash.com/photo-1517423440428-a5a00ad493e8");
                notification.setDate(new Date());
                notificationFeignClient.saveNotification(notification);
            }
        }
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
            if(null!=dto.getIdGroupe()){
                SimpleGroupeDTO groupe = groupeFeignClient.getSimpleGroupe(dto.getIdGroupe());
                dto.setGroupe(groupe);
            }
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

            surveyToUpdate.setStartDate(survey.getStartDate());
            surveyToUpdate.setEndDate(survey.getEndDate());
            survey.setUpdatedAt(LocalDateTime.now());

            if(survey.getIdGroupe()!=surveyToUpdate.getIdGroupe()){
                GroupeDTO oldGroupe = groupeFeignClient.getGroupe(surveyToUpdate.getIdGroupe());
                if(null!=surveyToUpdate.getIdGroupe()){
                    for(SimpleUserDTO user: oldGroupe.getMembres()){
                        List<UserDeviceDTO> devices = userDeviceFeignClient.getDevice(user.getIdUser());
                        for (UserDeviceDTO device:devices){
                            NotificationDTO notification = new NotificationDTO();
                            notification.setTitle("🚫 Your Group "+oldGroupe.getLabel()+" excluded from Survey");
                            notification.setDescription(oldGroupe.getLabel()+"excluded from Survey "+surveyToUpdate.getTitle());
                            notification.setType(11);
                            notification.setToken(device.getToken());
                            notification.setBadgeCount(1);
                            notification.setImageUrl("https://images.unsplash.com/photo-1517423440428-a5a00ad493e8");
                            notification.setDate(new Date());
                            notification.setIdReciver(user.getIdUser());
                            notificationFeignClient.saveNotification(notification);
                        }
                    }
                }
                GroupeDTO newGroupe = groupeFeignClient.getGroupe(survey.getIdGroupe());
                for(SimpleUserDTO user: newGroupe.getMembres()){
                    List<UserDeviceDTO> devices = userDeviceFeignClient.getDevice(user.getIdUser());
                    for (UserDeviceDTO device:devices){
                        NotificationDTO notification = new NotificationDTO();
                        notification.setTitle("📊 Your Group "+newGroupe.getLabel()+" Added to a Survey");
                        notification.setDescription(newGroupe.getLabel()+"Added to new Survey "+surveyToUpdate.getTitle());
                        notification.setType(11);
                        notification.setToken(device.getToken());
                        notification.setBadgeCount(1);
                        notification.setImageUrl("https://images.unsplash.com/photo-1517423440428-a5a00ad493e8");
                        notification.setDate(new Date());
                        notification.setIdReciver(user.getIdUser());
                        notificationFeignClient.saveNotification(notification);
                    }
                }
            }
            surveyToUpdate.setIdGroupe(survey.getIdGroupe());
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
               // List<SurveyValueDTO> values = surveyValueFeignClient.getSurveyesBySurvey(dto.getIdSurvey());
               // dto.setValues(values);
               // List<VoteDTO> votes = voteFeignClient.getVoteBySurvey(dto.getIdSurvey());
               // dto.setVotes(votes);
                if(null!=survey.getIdGroupe()){
                    SimpleGroupeDTO groupe = groupeFeignClient.getSimpleGroupe(survey.getIdGroupe());
                    dto.setGroupe(groupe);
                }
                surveysDto.add(dto);
            }
            return surveysDto;
        }else {
            return new ArrayList<SurveyDTO>();
        }
    }

    @Override
    public int getCountSurveyByUser(Long idUser) {
        List<Survey> surveys = surveyRepository.findSurveyByIdUser(idUser);
        return surveys.size();
    }

    @Override
    public List<SurveyDTO> getALLSurveyByParticipant(Long idUser) {
        List<Long> idsGroupe = groupeUserFeignClient.getGroupebyMembre(idUser);

        List<Survey> surveys = surveyRepository.findSurveyByIdGroupeIn(idsGroupe);

        List<SurveyDTO> surveysDto= new ArrayList<>();
        if (surveys.size() > 0) {
            for (Survey survey : surveys) {
                SurveyDTO dto = SurveyMapper.mapToSurveyDto(survey);
                SimpleUserDTO userDto = userFeignClient.getSimpleUser(dto.getIdUser());
                dto.setUser(userDto);
               // List<SurveyValueDTO> values = surveyValueFeignClient.getSurveyesBySurvey(dto.getIdSurvey());
                //dto.setValues(values);
                //List<VoteDTO> votes = voteFeignClient.getVoteBySurvey(dto.getIdSurvey());
                //dto.setVotes(votes);
                if(null!=survey.getIdGroupe()){
                    SimpleGroupeDTO groupe = groupeFeignClient.getSimpleGroupe(survey.getIdGroupe());
                    dto.setGroupe(groupe);
                }
                surveysDto.add(dto);
            }
            return surveysDto;
        }else {
            return new ArrayList<SurveyDTO>();
        }
    }

    @Override
    public SimpleSurveyDTO getSimpleSurvey(Long idSurvey) throws SurveyException {
        Optional<Survey> surveyOptional = surveyRepository.findById(idSurvey);
        if (!surveyOptional.isPresent()) {
            throw new SurveyException(SurveyException.NotFoundException(idSurvey));
        }else {
            SimpleSurveyDTO dto =SurveyMapper.mapToSimpleSurveyDto(surveyOptional.get());
            SimpleUserDTO userDto = userFeignClient.getSimpleUser(dto.getIdUser());
            dto.setUser(userDto);
            return dto;
        }
    }

    @Override
    public int getCountSurveyByParticipant(Long idUser) {
        List<Long> idsGroupe = groupeUserFeignClient.getGroupebyMembre(idUser);
        List<Survey> surveys = surveyRepository.findSurveyByIdGroupeIn(idsGroupe);
        return surveys.size();
    }
}
