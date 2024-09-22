package com.msurvey_values.msurvey_values.controller;

import com.msurvey_values.msurvey_values.dto.SurveyValueDTO;
import com.msurvey_values.msurvey_values.exception.SurveyValueException;
import com.msurvey_values.msurvey_values.model.SurveyValue;
import com.msurvey_values.msurvey_values.service.SurveyValueService;
import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.events.EventException;

import java.util.ArrayList;
import java.util.List;
@RestController
@CrossOrigin("*")
@RequestMapping("survey_value")
@AllArgsConstructor
public class SurveyValueController {
    private SurveyValueService surveyValueService;
    @GetMapping(value = "/lowel")
    public String test(){
        return "hi";
    }
    @PostMapping
    public ResponseEntity<SurveyValueDTO> saveSurveyValue(@RequestBody SurveyValueDTO surveyValueDTO){
        SurveyValueDTO savedSurveyValue = surveyValueService.saveSurveyValue(surveyValueDTO);
        return new ResponseEntity<>(savedSurveyValue, HttpStatus.CREATED);
    }
    @GetMapping("")
    public ResponseEntity<?> getAllSurveyValue() {
        List<SurveyValueDTO> surveyValues = surveyValueService.getALLSurveyValue();
        return new ResponseEntity<>(surveyValues, surveyValues.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{idSurveyValue}")
    public ResponseEntity<SurveyValueDTO> getSurveyValue(@PathVariable("idSurveyValue") Long idSurveyValue) throws SurveyValueException {
        try {
            return new ResponseEntity<>(surveyValueService.getSurveyValue(idSurveyValue), HttpStatus.OK);
        } catch (SurveyValueException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new SurveyValueDTO());
        }
    }

    @DeleteMapping("/{idSurveyValue}")
    public ResponseEntity<?> deleteById(@PathVariable("idSurveyValue") Long idSurveyValue) throws SurveyValueException{
        try{
            surveyValueService.deleteSurveyValue(idSurveyValue);
            return new ResponseEntity<>("Successfully deleted surveyValue with id "+idSurveyValue, HttpStatus.OK);
        }
        catch (SurveyValueException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{idSurveyValue}")
    public ResponseEntity<?> updateById(@PathVariable("idSurveyValue") Long idSurveyValue, @RequestBody SurveyValue surveyValue)
    {
        try {
            surveyValueService.updateSurveyValue(idSurveyValue,surveyValue);
            return new ResponseEntity<>("Updated event with id "+idSurveyValue+"", HttpStatus.OK);
        }
        catch(ConstraintViolationException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch (SurveyValueException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/by_user/{idUser}")
    public ResponseEntity<List<SurveyValueDTO>> getSurveyeValueByUser(@PathVariable("idUser") Long idUser){
        List<SurveyValueDTO> surveyvalues = surveyValueService.getALLSurveyValueByUser(idUser);
        return new ResponseEntity<>(surveyvalues, surveyvalues.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/by_survey/{idSurvey}")
    public ResponseEntity<List<SurveyValueDTO>> getSurveyesBySurvey(@PathVariable("idSurvey") Long idSurvey){
        List<SurveyValueDTO> surveyvalues = surveyValueService.getALLSurveyValueBySurvey(idSurvey);
        if(surveyvalues.size() > 0 ){
            return new ResponseEntity<>(surveyvalues,  HttpStatus.OK );

        }else{

        }
        return new ResponseEntity<>(new ArrayList<SurveyValueDTO>(),  HttpStatus.OK );
    }
}
