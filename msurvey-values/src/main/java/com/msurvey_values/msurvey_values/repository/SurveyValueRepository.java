package com.msurvey_values.msurvey_values.repository;

import com.msurvey_values.msurvey_values.model.SurveyValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SurveyValueRepository extends JpaRepository<SurveyValue,Long> {
    List<SurveyValue> findSurveyValuesByIdSurvey(Long idSurvery);
    List<SurveyValue> findSurveyValuesByIdUser(Long idUser);
}
