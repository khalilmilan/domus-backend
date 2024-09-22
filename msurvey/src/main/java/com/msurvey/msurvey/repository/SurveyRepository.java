package com.msurvey.msurvey.repository;

import com.msurvey.msurvey.dto.SurveyDTO;
import com.msurvey.msurvey.model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SurveyRepository extends JpaRepository<Survey,Long> {
    List<Survey> findSurveyByIdUser(Long idUser);
}
