package com.msurvey.msurvey.service.client;

import com.msurvey.msurvey.dto.SurveyValueDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class SurveyValuefallback implements SurveyValueFeignClient{
    @Override
    public ResponseEntity<List<SurveyValueDTO>> getSurveyesBySurvey(Long idSurvey) {
        return null;
    }
}
