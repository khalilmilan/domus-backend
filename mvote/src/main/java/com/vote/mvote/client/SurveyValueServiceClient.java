package com.vote.mvote.client;


import com.vote.mvote.configurations.FeignClientSecurityConfig;
import com.vote.mvote.dto.SurveyValueDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * Feign client interface named {@link SurveyValueServiceClient} for interacting with the User Service.
 * Provides methods to validate tokens and retrieve authentication information.
 */
@FeignClient(name = "microservice-survey-values", path = "/survey_value", configuration = FeignClientSecurityConfig.class)
public interface SurveyValueServiceClient {
    @GetMapping(value = "/{idSurveyValue}",consumes = "application/json")
    SurveyValueDTO getSurveyValue(@PathVariable Long idSurveyValue);
}
