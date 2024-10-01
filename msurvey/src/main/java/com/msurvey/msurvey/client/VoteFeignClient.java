package com.msurvey.msurvey.client;

import com.msurvey.msurvey.configurations.FeignClientSecurityConfig;
import com.msurvey.msurvey.dto.SurveyValueDTO;
import com.msurvey.msurvey.dto.VoteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="microservice-vote", path = "/vote",configuration = FeignClientSecurityConfig.class)
public interface VoteFeignClient {
    @GetMapping(value = "/by_survey/{idSurvey}",consumes = "application/json")
    List<VoteDTO> getVoteBySurvey(@PathVariable("idSurvey") Long idSurvey);

}
