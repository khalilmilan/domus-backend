package com.msurvey.msurvey.service.client;

import com.msurvey.msurvey.dto.SurveyValueDTO;
import com.msurvey.msurvey.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="microservice-survey-values", fallback = SurveyValuefallback.class)
public interface SurveyValueFeignClient {
    @GetMapping(value = "/survey_value/by_survey/{idSurvey}",consumes = "application/json")
    ResponseEntity<List<SurveyValueDTO>> getSurveyesBySurvey(@PathVariable("idSurvey") Long idSurvey);

}
