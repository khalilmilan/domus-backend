package com.springbootmicroservices.userservice.client;
import com.springbootmicroservices.userservice.config.FeignClientSecurityConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservice-survey", path = "/survey", configuration = FeignClientSecurityConfig.class)

public interface SurveyServiceClient {
    @GetMapping(value = "/count_survey_by_user/{idUser}",consumes = "application/json")
    int getSurveyCountByUser(@PathVariable("idUser") Long idUser);

    @GetMapping(value = "/count_survey_by_participant/{idUser}",consumes = "application/json")
    int getSurveyCountByParticipant(@PathVariable("idUser") Long idUser);
}
