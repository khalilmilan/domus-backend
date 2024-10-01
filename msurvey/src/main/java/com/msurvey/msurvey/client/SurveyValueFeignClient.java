package com.msurvey.msurvey.client;

import com.msurvey.msurvey.configurations.FeignClientSecurityConfig;
import com.msurvey.msurvey.dto.SurveyValueDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="microservice-survey-values", path = "/survey_value",configuration = FeignClientSecurityConfig.class)
public interface SurveyValueFeignClient {
    @GetMapping(value = "/by_survey/{idSurvey}",consumes = "application/json")
    List<SurveyValueDTO> getSurveyesBySurvey(@PathVariable("idSurvey") Long idSurvey);

}
