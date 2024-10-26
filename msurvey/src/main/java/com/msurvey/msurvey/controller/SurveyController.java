package com.msurvey.msurvey.controller;

import com.msurvey.msurvey.dto.SurveyDTO;
import com.msurvey.msurvey.exception.SurveyException;
import com.msurvey.msurvey.model.Survey;
import com.msurvey.msurvey.service.SurveyService;
import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("survey")
@AllArgsConstructor
public class SurveyController {
    private SurveyService surveyService;
    @GetMapping(value = "/lowel")
    public String test(){
        return "hi";
    }
    @PostMapping
    public ResponseEntity<SurveyDTO> saveSurvey(@RequestBody SurveyDTO surveyDTO){
        SurveyDTO savedSurvey = surveyService.saveSurvey(surveyDTO);
        return new ResponseEntity<>(savedSurvey, HttpStatus.CREATED);
    }
    @GetMapping("")
    public ResponseEntity<?> getAllSurveys() {
        List<SurveyDTO> surveys = surveyService.getALLSurvey();
        return new ResponseEntity<>(surveys, surveys.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{idSurvey}")
    public ResponseEntity<SurveyDTO> getSurvey(@PathVariable("idSurvey") Long idSurvey) throws SurveyException {
        try {
            return new ResponseEntity<>(surveyService.getSurvey(idSurvey), HttpStatus.OK);
        } catch (SurveyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new SurveyDTO());
        }
    }

    @DeleteMapping("/{idSurvey}")
    public ResponseEntity<?> deleteById(@PathVariable("idSurvey") Long idSurvey) throws SurveyException{
        try{
            surveyService.deleteSurvey(idSurvey);
            return new ResponseEntity<>("Successfully deleted survey with id "+idSurvey, HttpStatus.OK);
        }
        catch (SurveyException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{idSurvey}")
    public ResponseEntity<?> updateById(@PathVariable("idSurvey") Long idSurvey, @RequestBody Survey survey)
    {
        try {
            surveyService.updateSurvey(idSurvey,survey);
            return new ResponseEntity<>("Updated survery with id "+idSurvey+"", HttpStatus.OK);
        }
        catch(ConstraintViolationException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch (SurveyException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/by_user/{idUser}")
    public ResponseEntity<List<SurveyDTO>> getSurveyesByUser(@PathVariable("idUser") Long idUser){
        List<SurveyDTO> surveys = surveyService.getALLSurveyByUser(idUser);
        return new ResponseEntity<>(surveys, surveys.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
    @GetMapping("/count_survey_by_user/{idUser}")
    public int getSurveyCountByUser(@PathVariable("idUser") Long idUser){
        return surveyService.getCountSurveyByUser(idUser);
    }
    @GetMapping("/by_participant/{idUser}")
    public List<SurveyDTO> getALLSurveyByParticipant(@PathVariable("idUser") Long idUser){
        return surveyService.getALLSurveyByParticipant(idUser);
    }

}
