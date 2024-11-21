package com.vote.mvote.client;

import com.vote.mvote.configurations.FeignClientSecurityConfig;
import com.vote.mvote.dto.NotificationDTO;
import com.vote.mvote.dto.SimpleSurveyDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="microservice-survey",path = "/survey",configuration = FeignClientSecurityConfig.class)

public interface SurveyFeignClient {

    @GetMapping(value = "/simple_survey/{idSurvey}",consumes = "application/json")
    SimpleSurveyDTO getSimpleSurvey(@PathVariable("idSurvey") Long idSurvey);
}
